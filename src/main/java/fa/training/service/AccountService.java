package fa.training.service;

import fa.training.dto.account.AccountDto;

public interface AccountService {
    AccountDto save(AccountDto accountDto);

    boolean existsByEmail(String account);

    boolean existsByAccount(String account);
}
