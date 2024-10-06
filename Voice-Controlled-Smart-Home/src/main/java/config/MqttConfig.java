package config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Value("${mqtt.broker}")
    private String brokerUrl;

    @Value("${mqtt.clientId}")
    private String clientId;

    @Bean
    public IMqttClient mqttClient() throws MqttException {
        return new MqttClient(brokerUrl, clientId);
    }

    public void publish(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttClient().publish(topic, mqttMessage);
    }

    public void subscribe(String topic) throws MqttException {
        mqttClient().subscribe(topic, (t, msg) -> {
            System.out.println("Received message: " + new String(msg.getPayload()));
            // Handle message (e.g., update device status)
        });
    }
}
