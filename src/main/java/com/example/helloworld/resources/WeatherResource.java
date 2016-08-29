package com.example.helloworld.resources;

import com.example.helloworld.core.Temperature;
import com.example.helloworld.db.TemperatureDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    private final TemperatureDAO temperatureDAO;

    public WeatherResource(TemperatureDAO temperatureDAO) {
        this.temperatureDAO = temperatureDAO;
    }

    @POST
    @UnitOfWork
    public Temperature createTemperature(Temperature temperature) {
        return temperatureDAO.create(temperature);
    }

    @GET
    @UnitOfWork
//    public List<Temperature> listTemperature() {
//        return temperatureDAO.findAll();
//    }
    public List<Temperature> listTemperature() {
        return "It is 90 degrees right now!";
    }

}
