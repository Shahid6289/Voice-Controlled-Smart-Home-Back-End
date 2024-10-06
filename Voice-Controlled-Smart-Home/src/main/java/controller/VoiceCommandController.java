package controller;

import model.VoiceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VoiceCommandService;

import java.util.List;

@RestController
@RequestMapping("/api/voice-commands")
public class VoiceCommandController {

    @Autowired
    private VoiceCommandService voiceCommandService;

    @GetMapping
    public ResponseEntity<List<VoiceCommand>> getAllVoiceCommands() {
        return ResponseEntity.ok(voiceCommandService.getAllVoiceCommands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoiceCommand> getVoiceCommandById(@PathVariable Long id) {
        VoiceCommand voiceCommand = voiceCommandService.getVoiceCommandById(id);
        return voiceCommand != null ? ResponseEntity.ok(voiceCommand) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VoiceCommand> createVoiceCommand(@RequestBody VoiceCommand voiceCommand) {
        return ResponseEntity.ok(voiceCommandService.createVoiceCommand(voiceCommand));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoiceCommand> updateVoiceCommand(@PathVariable Long id, @RequestBody VoiceCommand updatedVoiceCommand) {
        VoiceCommand voiceCommand = voiceCommandService.updateVoiceCommand(id, updatedVoiceCommand);
        return voiceCommand != null ? ResponseEntity.ok(voiceCommand) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiceCommand(@PathVariable Long id) {
        voiceCommandService.deleteVoiceCommand(id);
        return ResponseEntity.noContent().build();
    }
}
