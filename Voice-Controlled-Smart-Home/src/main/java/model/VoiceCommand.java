package model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "voice_commands")
public class VoiceCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commandText; // e.g., "Turn on the living room lights"

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // The user who gave the voice command

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device; // The device controlled by the command (if applicable)

    @ManyToOne
    @JoinColumn(name = "scene_id")
    private Scene scene; // The scene controlled by the command (if applicable)
}