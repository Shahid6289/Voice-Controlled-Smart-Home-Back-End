package service;

import model.AutomationRule;
import org.springframework.stereotype.Service;
import repository.AutomationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;
import java.util.List;

@Service
public class AutomationService {

    @Autowired
    private AutomationRepository automationRepository;

    @Autowired
    private DeviceService deviceService;

    public AutomationRule addAutomation(AutomationRule automation) {
        return automationRepository.save(automation);
    }

    public List<AutomationRule> getAutomationsByUser(Long userId) {
        return automationRepository.findByUserId(userId);
    }

    public void deleteAutomation(Long automationId) {
        automationRepository.deleteById(automationId);
    }

    // Scheduled task to check automations and execute them
    @Scheduled(cron = "0 * * * * *") // Runs every minute
    public void checkAutomations() {
        List<AutomationRule> automations = automationRepository.findByActiveTrue();
        for (AutomationRule automation : automations) {
            if (LocalTime.now().isAfter(automation.getTriggerTime())) {
                try {
                    deviceService.updateDeviceStatus(automation.getDevice().getId(), automation.isTargetStatus());
                    automation.setActive(false); // Disable once executed (can be modified to repeat if necessary)
                    automationRepository.save(automation);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
