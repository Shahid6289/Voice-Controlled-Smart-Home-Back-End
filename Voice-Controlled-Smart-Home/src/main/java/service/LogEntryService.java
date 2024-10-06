package service;

import model.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LogEntryRepository;

import java.util.List;

@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public List<LogEntry> getAllLogEntries() {
        return logEntryRepository.findAll();
    }

    public LogEntry getLogEntryById(Long id) {
        return logEntryRepository.findById(id).orElse(null);
    }

    public LogEntry createLogEntry(LogEntry logEntry) {
        return logEntryRepository.save(logEntry);
    }

    public void deleteLogEntry(Long id) {
        logEntryRepository.deleteById(id);
    }
}
