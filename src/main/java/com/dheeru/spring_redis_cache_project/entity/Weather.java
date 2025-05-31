package com.dheeru.spring_redis_cache_project.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String forecast;

    public Weather() {
    }

    public Weather(String city, String forecast, Long id) {
        this.city = city;
        this.forecast = forecast;
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getForecast() {
        return forecast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }
}
