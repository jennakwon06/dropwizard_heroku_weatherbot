package com.example.helloworld.db;

import com.example.helloworld.core.Temperature;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class TemperatureDAO extends AbstractDAO<Temperature> {
    public TemperatureDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Temperature> findByAreaCode(Long areacode) {
        return Optional.ofNullable(get(areacode));
    }

    public Temperature create(Temperature temperature) {
        return persist(temperature);
    }

    public List<Temperature> findAll() {
        return list(namedQuery("com.example.helloworld.core.Temperature.findAll"));
    }
}
