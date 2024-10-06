package controller;

import model.Scene;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SceneService;

import java.util.List;

@RestController
@RequestMapping("/api/scenes")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @PostMapping("/add")
    public ResponseEntity<Scene> addScene(@RequestBody Scene scene) {
        return ResponseEntity.ok(sceneService.addScene(scene));
    }

    @GetMapping("/list")
    public List<Scene> listScenes(@RequestParam Long userId) {
        return sceneService.getScenesByUser(userId);
    }

    @PostMapping("/activate/{sceneId}")
    public ResponseEntity<String> activateScene(@PathVariable Long sceneId) {
        try {
            sceneService.activateScene(sceneId);
            return ResponseEntity.ok("Scene activated");
        } catch (MqttException e) {
            return ResponseEntity.status(500).body("Error activating scene");
        }
    }
}
