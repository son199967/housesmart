package vn.com.smart.house.demo.ServiceImpl;

import jdk.net.SocketFlow;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.smart.house.demo.Model.Control;
import vn.com.smart.house.demo.Model.Garden;
import vn.com.smart.house.demo.Model.StatusLed;
import vn.com.smart.house.demo.Model.WeatherInfo;
import vn.com.smart.house.demo.Repository.ControlRepository;
import vn.com.smart.house.demo.Repository.GardenRepository;
import vn.com.smart.house.demo.Repository.SmartRepository;
import vn.com.smart.house.demo.Service.SmartHouseService;
import vn.com.smart.house.demo.config.Application;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SmartHouseServiceImpl implements SmartHouseService {

    private GardenRepository gardenRepository;
    private ControlRepository controlRepository;
    private SmartRepository smartRepository;
    private Application application;


    @Autowired
    public SmartHouseServiceImpl(GardenRepository gardenRepository, ControlRepository controlRepository, SmartRepository smartRepository,Application application) {
        this.gardenRepository = gardenRepository;
        this.controlRepository = controlRepository;
        this.smartRepository = smartRepository;
        this.application = application;
        this.start();
    }
    @Override
    @Transactional(rollbackOn = Exception.class)
    public String openLight(Long ledNumber, StatusLed status) {
        Optional<Control> controlOptional = controlRepository.findById(ledNumber);
        if (!controlOptional.isPresent()){
            return "Not Found led";
        }
        Control control1 = controlOptional.get();
        control1.setStatusLed(StatusLed.on);
        try {
            application.publish( ledNumber+","+status);
        }catch (Exception e){
            e.printStackTrace();
        }
        controlRepository.save(control1);
        return "Led "+ledNumber+"is "+status.name();
    }


    @Override
    public String deleteWhere(Long id) {
        smartRepository.deleteAll();
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public String closeLight(Long ledNumber, StatusLed statusLed) {
        Optional<Control> controlOptional = controlRepository.findById(ledNumber);
        if (!controlOptional.isPresent()){
            return "Not Found led";
        }
        Control control1 = controlOptional.get();
        control1.setStatusLed(StatusLed.off);
        try {
            application.publish(ledNumber+","+statusLed);
        }catch (Exception e){
            e.printStackTrace();
        }
        controlRepository.save(control1);
        return "Led "+ledNumber+"is "+statusLed.name();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public WeatherInfo getWeatherNow() {
//        Garden garden = gardenRepository.findById(1L).get();
//        String temp = null;
//        String hum = null;
//        try {
//            temp =  application.subscribeTem()[0];
//            hum = application.subscribeHUMI()[0];
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("temp:"+temp+"hum"+hum);
//        WeatherInfo weatherInfo = new WeatherInfo();
//        weatherInfo.setGarden(garden);
//        weatherInfo.setHumidity(Double.parseDouble(hum));
//        weatherInfo.setTemperature(Double.parseDouble(temp));
//        weatherInfo.setTime(LocalDateTime.now());
//        return weatherInfo;
        WeatherInfo weatherInfo = smartRepository.findTopByGardenIdOrderByTimeDesc(1l);
        return weatherInfo;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<WeatherInfo> getWeather() {
      List<WeatherInfo> weatherInfos = smartRepository.findAll();
      return weatherInfos;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public WeatherInfo updateWeather() {
//        Garden garden = gardenRepository.findById(1L).get();
//        String temp = null;
//        String hum = null;
//        try {
//            temp =  application.subscribeTem()[0];
//            hum = application.subscribeHUMI()[0];
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        WeatherInfo weatherInfo = new WeatherInfo();
//        weatherInfo.setGarden(garden);
//        weatherInfo.setHumidity(Double.parseDouble(hum));
//        weatherInfo.setTemperature(Double.parseDouble(temp));
//        weatherInfo.setTime(LocalDateTime.now());
//        smartRepository.save(weatherInfo);
//        System.out.println("wea
//        ther is tem:"+temp+" humatary:"+hum);
//       return weatherInfo;
        return null;
    }
    private void start(){

        Garden garden = new Garden();
        garden.setId(1L);
        gardenRepository.save(garden);
        Control control = new Control();
        control.setGarden(garden);
        control.setStatusLed(StatusLed.off);
        control.setId(16L);
        Control control2 = new Control();
        control2.setGarden(garden);
        control2.setStatusLed(StatusLed.off);
        control2.setId(17L);
        controlRepository.save(control);
        controlRepository.save(control2);


    }
}

