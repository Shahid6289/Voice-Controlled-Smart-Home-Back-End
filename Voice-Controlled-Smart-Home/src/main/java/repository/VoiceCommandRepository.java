package repository;

import model.VoiceCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceCommandRepository extends JpaRepository<VoiceCommand, Long> {
}
