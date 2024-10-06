package controller;

import model.Device;
import org.eclipse.paho.client.mqttv3.MqttException;
import service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/add")
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        return ResponseEntity.ok(deviceService.addDevice(device));
    }

    @GetMapping("/list")
    public List<Device> listDevices(@RequestParam Long userId) {
        return deviceService.getDevicesByUser(userId);
    }

    @PostMapping("/status/{deviceId}")
    public ResponseEntity<String> updateDeviceStatus(@PathVariable Long deviceId, @RequestBody boolean status) throws MqttException {
        deviceService.updateDeviceStatus(deviceId, status);
        return ResponseEntity.ok("Device status updated");
    }
}
