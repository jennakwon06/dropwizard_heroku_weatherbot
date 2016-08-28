package com.example.helloworld.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "temperatures")
@NamedQueries(
    {
            @NamedQuery(
                    name = "com.example.helloworld.core.Temperature.findAll",
                    query = "SELECT p FROM Temperature p"
            )
    }
)
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "areaCode", nullable = false)
    private String areaCode;

    @Column(name = "temperature", nullable = false)
    private String temperature;

    public Temperature() {
    }

    public Temperature(String areaCode, String temperature) {
        this.areaCode = areaCode;
        this.temperature = temperature;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String fullName) {
        this.areaCode = fullName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String jobTitle) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Temperature)) {
            return false;
        }

        final Temperature that = (Temperature) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.areaCode, that.areaCode) &&
                Objects.equals(this.temperature, that.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areaCode, temperature);
    }
}
