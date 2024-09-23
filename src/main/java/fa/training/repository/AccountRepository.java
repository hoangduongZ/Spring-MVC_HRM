package fa.training.repository;

import fa.training.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByAccount(String account);
    Account findByEmail(String email);
}
