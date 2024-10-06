package repository;

import model.AutomationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutomationRepository extends JpaRepository<AutomationRule, Long> {
    List<AutomationRule> findByUserId(Long userId);
    List<AutomationRule> findByActiveTrue();

}
