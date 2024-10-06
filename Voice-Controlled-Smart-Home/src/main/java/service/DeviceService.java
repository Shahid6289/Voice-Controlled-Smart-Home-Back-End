package service;

import config.MqttConfig;
import model.Device;
import org.eclipse.paho.client.mqttv3.MqttException;
import repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private MqttConfig mqttConfig;

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public List<Device> getDevicesByUser(Long userId) {
        return deviceRepository.findByUserId(userId);
    }

    public void updateDeviceStatus(Long deviceId, boolean status) throws MqttException {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.setStatus(status);
        deviceRepository.save(device);

        // Publish to MQTT broker
        String topic = "/smartHome/devices/" + device.getId();
        mqttConfig.publish(topic, "Status: " + (status ? "ON" : "OFF"));
    }
}
