package com.dheeru.spring_redis_cache_project.service;

import com.dheeru.spring_redis_cache_project.entity.Weather;
import com.dheeru.spring_redis_cache_project.repository.WeatherRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Cacheable(condition = "#city != null", value = "weather", key = "#city")
    public String getWeather(String city) {
        System.out.println("Fetching weather data for city: " + city);
        Optional<Weather> weather = weatherRepository.findByCity(city);
        return weather.map(Weather::getForecast).orElse("Weather data not available");
    }

    public Weather saveWeather(Weather weather) {
        weatherRepository.save(weather);
        return weather;
    }

    @CachePut(condition = "#city != null", value = "weather", key = "#city")
    public String updateWeather(String city, String weather) {
        weatherRepository.findByCity(city).ifPresent(w -> {
            w.setForecast(weather);
            weatherRepository.save(w);
        });
        return weather;
    }

    @Transactional
    @CacheEvict(condition = "#city != null", value = "weather", key = "#city")
    public void deleteWeather(String city) {
        weatherRepository.deleteByCity(city);
    }
}
