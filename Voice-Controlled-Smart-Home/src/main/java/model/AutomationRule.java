package model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "automation_rules")
public class AutomationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conditionType; // e.g., "time", "weather"
    private LocalTime triggerTime; // Trigger time for time-based rules
    private boolean targetStatus; // Target status for device (on/off)
    private boolean active; // Whether the rule is currently active
    private String conditionKey; // Additional condition information, e.g., "time" for time-based rule

    private String conditionValue; // Additional condition information, e.g., "rainy" for weather-based rule

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device; // The device this rule controls
}
