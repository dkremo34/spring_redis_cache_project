package com.dheeru.spring_redis_cache_project.repository;

import com.dheeru.spring_redis_cache_project.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findByCity(String city);
    void deleteByCity(String city);
}
