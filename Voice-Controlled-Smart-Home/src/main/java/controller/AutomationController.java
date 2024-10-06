package controller;

import model.AutomationRule;
import service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automations")
public class AutomationController {

    @Autowired
    private AutomationService automationService;

    @PostMapping("/add")
    public ResponseEntity<AutomationRule> addAutomation(@RequestBody AutomationRule automation) {
        return ResponseEntity.ok(automationService.addAutomation(automation));
    }

    @GetMapping("/list")
    public List<AutomationRule> listAutomations(@RequestParam Long userId) {
        return automationService.getAutomationsByUser(userId);
    }

    @DeleteMapping("/delete/{automationId}")
    public ResponseEntity<String> deleteAutomation(@PathVariable Long automationId) {
        automationService.deleteAutomation(automationId);
        return ResponseEntity.ok("Automation deleted");
    }
}
