package fa.training.repository;

import fa.training.entity.Account;
import fa.training.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByAccount(String account);
    Account findByEmail(String email);
    Optional<Account> findByEmployee(Employee employee);
}
