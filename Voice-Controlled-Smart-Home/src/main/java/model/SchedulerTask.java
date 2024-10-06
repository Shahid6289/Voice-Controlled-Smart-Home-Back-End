package model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "scheduler_tasks")
public class SchedulerTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime scheduledTime;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    private boolean isRecurring; // True if this task should repeat, e.g., daily
}
