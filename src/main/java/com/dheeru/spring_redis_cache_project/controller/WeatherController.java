package com.dheeru.spring_redis_cache_project.controller;


import com.dheeru.spring_redis_cache_project.entity.Weather;
import com.dheeru.spring_redis_cache_project.service.CacheInspectionService;
import com.dheeru.spring_redis_cache_project.service.CacheRedisMonitorService;
import com.dheeru.spring_redis_cache_project.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final CacheInspectionService cacheInspectionService;
    private final CacheRedisMonitorService cacheRedisMonitorService;

    public WeatherController(WeatherService weatherService, CacheInspectionService cacheInspectionService, CacheRedisMonitorService cacheRedisMonitorService) {
        this.weatherService = weatherService;
        this.cacheInspectionService = cacheInspectionService;
        this.cacheRedisMonitorService = cacheRedisMonitorService;
    }

    @GetMapping
    public String getWeather(@RequestParam String city) {
        return weatherService.getWeather(city);
    }

    @PostMapping
    public Weather addWeather(@RequestBody Weather weather) {
        Weather dsfsd = weatherService.saveWeather(weather);
        return dsfsd;
    }

    @GetMapping("/cache")
    public void getCacheData(@RequestParam String city) {
        cacheInspectionService.inspectCaches("weather");
    }

    @PutMapping("/{city}")
    public String updateWeather(@PathVariable String city, @RequestParam String weather) {
        weatherService.updateWeather(city, weather);
        return "Weather data updated: " + weather;
    }

    @DeleteMapping("/{city}")
    public String deleteWeather(@PathVariable String city) {
        weatherService.deleteWeather(city);
        return "Weather data deleted for city: " + city;
    }

    @GetMapping("/all")
    public String getAllRedisData() {
         return "Weather data deleted for city: " + cacheRedisMonitorService.getAllCacheKeys();
    }

}
