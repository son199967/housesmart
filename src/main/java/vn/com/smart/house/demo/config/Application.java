package vn.com.smart.house.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import vn.com.smart.house.demo.Model.JsonMap;
import vn.com.smart.house.demo.Model.WeatherInfo;
import vn.com.smart.house.demo.Repository.GardenRepository;
import vn.com.smart.house.demo.Repository.SmartRepository;

import java.time.LocalDateTime;
import java.util.concurrent.*;

@Component
public class Application {
    @Autowired
    private SmartRepository smartRepository;
    @Autowired
    private GardenRepository gardenRepository;
    final String serverUrl = "tcp://broker.mqttdashboard.com:1883";     /* ssl://mqtt.cumulocity.com:8883 for a secure connection */
    final String clientId = "sonnguyen-99";
    final String device_name = "My Java MQTT device";
    final String username = "ohtech.vn";
    final String password = "66668888";
    final String MQTT_TOPIC ="N11/dht11/humidity-temperature";
    final String MQTT_TEMPERATURE_TOPIC = "N11/dht11/temperature";
    final String MQTT_HUMIDITY_TOPIC = "N11/dht11/humidity";
    final String MQTT_LED_TOPIC = "N11/led";
    private MqttClient client = null;

    @PostConstruct
    private void SetUp() throws MqttException, InterruptedException {

        final MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        client = new MqttClient(serverUrl, clientId, null);
        client.connect(options);
        System.out.println(client+": is ready");
        subscribeTem();
//        Thread.sleep(10000);
//        do {
//            Thread.sleep(1000);
//            this.publish("RED");
//            String[] tem =   this.subscribe();
//            for (String tems:tem){
//                if (tems!=null)
//                System.out.println("tem {} " +tems);
//            }
//
//        }while (1==1);




    }

    public void publish(String message) throws MqttException {
       client.publish(MQTT_LED_TOPIC, new MqttMessage(message.getBytes()));
        System.out.println("Open Light :"+message);

    }

    public String subscribeTem() throws MqttException {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    client.subscribe(MQTT_TOPIC, new IMqttMessageListener() {
                        @Override
                        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                            String payload = new String(mqttMessage.getPayload());
                            System.out.println("Received operation " + payload);
                            JsonMap jsonMap = new ObjectMapper().readValue(payload,JsonMap.class);
                            WeatherInfo weatherInfo = new WeatherInfo();
                            weatherInfo.setTime(LocalDateTime.now());
                            weatherInfo.setTemperature(jsonMap.getTemperature());
                            weatherInfo.setHumidity(jsonMap.getHumidity());
                            weatherInfo.setLight(jsonMap.getLight());
                            weatherInfo.setGarden(gardenRepository.findById(1l).get());
                            System.out.println(weatherInfo.toString());
                            smartRepository.save(weatherInfo);

                        }
                    });
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 7, TimeUnit.SECONDS);
     return null;
    }


}
