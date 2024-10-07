package fa.training.repository;

import fa.training.entity.Account;
import fa.training.entity.Employee;
import fa.training.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, UUID> {
    Optional<TimeSheet> findByAccountAndDate(Account account,LocalDate date );

    List<TimeSheet> findByAccountAndDateBetween(Account account, LocalDate startDate, LocalDate endDate);

}
