package service;

import model.VoiceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.VoiceCommandRepository;

import java.util.List;

@Service
public class VoiceCommandService {

    @Autowired
    private VoiceCommandRepository voiceCommandRepository;

    public List<VoiceCommand> getAllVoiceCommands() {
        return voiceCommandRepository.findAll();
    }

    public VoiceCommand getVoiceCommandById(Long id) {
        return voiceCommandRepository.findById(id).orElse(null);
    }

    public VoiceCommand createVoiceCommand(VoiceCommand voiceCommand) {
        return voiceCommandRepository.save(voiceCommand);
    }

    public VoiceCommand updateVoiceCommand(Long id, VoiceCommand updatedVoiceCommand) {
        if (voiceCommandRepository.existsById(id)) {
            updatedVoiceCommand.setId(id);
            return voiceCommandRepository.save(updatedVoiceCommand);
        }
        return null;
    }

    public void deleteVoiceCommand(Long id) {
        voiceCommandRepository.deleteById(id);
    }
}
