package vn.com.smart.house.demo.Service;

import org.eclipse.paho.client.mqttv3.MqttException;
import vn.com.smart.house.demo.Model.Control;
import vn.com.smart.house.demo.Model.StatusLed;
import vn.com.smart.house.demo.Model.WeatherInfo;

import java.util.List;

public interface SmartGardenService {
    String openLight(Long id, StatusLed statusLed) ;
    String deleteWeather(Long id);
    String closeLight(Long id, StatusLed statusLed) ;
    WeatherInfo getWeatherNow();
    List<WeatherInfo> getWeather();
    WeatherInfo updateWeather();
    List<Control> getControls();
    void autoWatering(double humidity) throws MqttException;
}
