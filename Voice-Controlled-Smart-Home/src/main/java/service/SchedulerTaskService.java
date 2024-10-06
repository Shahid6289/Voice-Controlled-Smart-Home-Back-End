package service;

import model.SchedulerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SchedulerTaskRepository;

import java.util.List;

@Service
public class SchedulerTaskService {

    @Autowired
    private SchedulerTaskRepository schedulerTaskRepository;

    public List<SchedulerTask> getAllSchedulerTasks() {
        return schedulerTaskRepository.findAll();
    }

    public SchedulerTask getSchedulerTaskById(Long id) {
        return schedulerTaskRepository.findById(id).orElse(null);
    }

    public SchedulerTask createSchedulerTask(SchedulerTask schedulerTask) {
        return schedulerTaskRepository.save(schedulerTask);
    }

    public SchedulerTask updateSchedulerTask(Long id, SchedulerTask updatedSchedulerTask) {
        if (schedulerTaskRepository.existsById(id)) {
            updatedSchedulerTask.setId(id);
            return schedulerTaskRepository.save(updatedSchedulerTask);
        }
        return null;
    }

    public void deleteSchedulerTask(Long id) {
        schedulerTaskRepository.deleteById(id);
    }
}
