package fa.training.service;

import fa.training.dto.account.AccountDto;
import fa.training.entity.Account;

import java.util.Optional;

public interface AccountService {
    AccountDto save(AccountDto accountDto);

    boolean existsByEmail(String account);

    boolean existsByAccount(String account);
}
