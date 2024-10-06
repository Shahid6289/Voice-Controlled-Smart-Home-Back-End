package controller;

import model.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.LogEntryService;

import java.util.List;

@RestController
@RequestMapping("/api/log-entries")
public class LogEntryController {

    @Autowired
    private LogEntryService logEntryService;

    @GetMapping
    public ResponseEntity<List<LogEntry>> getAllLogEntries() {
        return ResponseEntity.ok(logEntryService.getAllLogEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogEntry> getLogEntryById(@PathVariable Long id) {
        LogEntry logEntry = logEntryService.getLogEntryById(id);
        return logEntry != null ? ResponseEntity.ok(logEntry) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LogEntry> createLogEntry(@RequestBody LogEntry logEntry) {
        return ResponseEntity.ok(logEntryService.createLogEntry(logEntry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogEntry(@PathVariable Long id) {
        logEntryService.deleteLogEntry(id);
        return ResponseEntity.noContent().build();
    }
}
