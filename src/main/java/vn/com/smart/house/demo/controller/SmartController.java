package vn.com.smart.house.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.com.smart.house.demo.Model.StatusLed;
import vn.com.smart.house.demo.Model.WeatherInfo;
import vn.com.smart.house.demo.Service.SmartHouseService;
import java.util.List;

@Controller
public class SmartController {
    private SmartHouseService smartService;

    @Autowired
    public SmartController(SmartHouseService smartService) {
        this.smartService = smartService;
    }

    @PutMapping(value = "/openLight")
    private ResponseEntity<String> openLight(@RequestParam Long id, @RequestParam StatusLed statusLed){
        String response = smartService.openLight(id,statusLed);
        return  ResponseEntity.ok(response);
    }
    @DeleteMapping(value = "/deleteWeather")
    private ResponseEntity<String> deleteWeather(@RequestParam Long id){
        String response = smartService.deleteWhere(id);
        return  ResponseEntity.ok(response);
    }
    @PostMapping(value = "/closeLight")
    private ResponseEntity<String> closeLight(@RequestParam Long id, @RequestParam StatusLed statusLed){
        String response = smartService.openLight(id,statusLed);
        return  ResponseEntity.ok(response);
    }
    @GetMapping(value = "/updateWeather")
    private ResponseEntity<WeatherInfo> updateWeather(){
       WeatherInfo weatherInfo = smartService.getWeatherNow();
        return  ResponseEntity.ok(weatherInfo);
    }
    @GetMapping(value = "/GetAllWeatherInfo")
    private ResponseEntity<List<WeatherInfo>> getWeather(){
        List<WeatherInfo> weatherInfo = smartService.getWeather();
        return  ResponseEntity.ok(weatherInfo);
    }


}
