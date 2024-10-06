package controller;

import model.SchedulerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SchedulerTaskService;

import java.util.List;

@RestController
@RequestMapping("/api/scheduler-tasks")
public class SchedulerTaskController {

    @Autowired
    private SchedulerTaskService schedulerTaskService;

    @GetMapping
    public ResponseEntity<List<SchedulerTask>> getAllSchedulerTasks() {
        return ResponseEntity.ok(schedulerTaskService.getAllSchedulerTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulerTask> getSchedulerTaskById(@PathVariable Long id) {
        SchedulerTask task = schedulerTaskService.getSchedulerTaskById(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SchedulerTask> createSchedulerTask(@RequestBody SchedulerTask schedulerTask) {
        return ResponseEntity.ok(schedulerTaskService.createSchedulerTask(schedulerTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedulerTask> updateSchedulerTask(@PathVariable Long id, @RequestBody SchedulerTask updatedSchedulerTask) {
        SchedulerTask task = schedulerTaskService.updateSchedulerTask(id, updatedSchedulerTask);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedulerTask(@PathVariable Long id) {
        schedulerTaskService.deleteSchedulerTask(id);
        return ResponseEntity.noContent().build();
    }
}
