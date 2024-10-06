package repository;

import model.SchedulerTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerTaskRepository extends JpaRepository<SchedulerTask, Long> {
}
