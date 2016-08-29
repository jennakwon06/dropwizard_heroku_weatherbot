package com.example.weatherbot.resources;

import com.example.weatherbot.core.Temperature;
import com.example.weatherbot.db.TemperatureDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {
    private Random r = new Random();

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
    public String getHardcodedMessage() {
        return "It is " + r.nextInt(100 - 50) + 50 + " degrees right now!";
    }
}
