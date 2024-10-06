package service;

import model.Device;
import model.Scene;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SceneRepository;

import java.util.List;
import java.util.Set;

@Service
public class SceneService {

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private DeviceService deviceService;

    public Scene addScene(Scene scene) {
        return sceneRepository.save(scene);
    }

    public List<Scene> getScenesByUser(Long userId) {
        return sceneRepository.findByUserId(userId);
    }

    public void activateScene(Long sceneId) throws MqttException {
        Scene scene = sceneRepository.findById(sceneId).orElseThrow();
        List<Device> devices = scene.getDevices();

        // Activate all devices in the scene
        for (Device device : devices) {
            deviceService.updateDeviceStatus(device.getId(), true); // Assume scene turns devices ON (can be made dynamic)
        }
    }
}
