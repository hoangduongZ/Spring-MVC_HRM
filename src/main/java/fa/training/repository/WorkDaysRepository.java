package fa.training.repository;

import fa.training.entity.Account;
import fa.training.entity.WorkDays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WorkDaysRepository extends JpaRepository<WorkDays, UUID> {
    Optional<WorkDays> findByAccount(Account account);
}
