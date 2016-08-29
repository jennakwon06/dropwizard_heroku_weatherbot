package com.example.weatherbot;

import com.example.weatherbot.auth.ExampleAuthenticator;
import com.example.weatherbot.auth.ExampleAuthorizer;
import com.example.weatherbot.cli.RenderCommand;
import com.example.weatherbot.core.Temperature;
import com.example.weatherbot.core.Template;
import com.example.weatherbot.core.User;
import com.example.weatherbot.db.TemperatureDAO;
import com.example.weatherbot.filter.DateRequiredFeature;
import com.example.weatherbot.health.TemplateHealthCheck;
import com.example.weatherbot.resources.WeatherResource;
import com.example.weatherbot.tasks.EchoTask;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import java.util.Map;

public class WeatherBotApplication extends Application<WeatherBotConfiguration> {
    public static void main(String[] args) throws Exception {
        new WeatherBotApplication().run(args);
    }

    private final HibernateBundle<WeatherBotConfiguration> hibernateBundle =
            /**
             * This is for future development when temperature is retrieved from databases.
             */
        new HibernateBundle<WeatherBotConfiguration>(Temperature.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(WeatherBotConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };

    @Override
    public String getName() {
        return "Weather service";
    }

    @Override
    public void initialize(Bootstrap<WeatherBotConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addCommand(new RenderCommand());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<WeatherBotConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(WeatherBotConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<WeatherBotConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(WeatherBotConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(WeatherBotConfiguration configuration, Environment environment) {
        final TemperatureDAO dao = new TemperatureDAO(hibernateBundle.getSessionFactory());
        final Template template = configuration.buildTemplate();

        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.admin().addTask(new EchoTask());
        environment.jersey().register(DateRequiredFeature.class);
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new ExampleAuthenticator())
                .setAuthorizer(new ExampleAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new WeatherResource(dao));
    }
}
