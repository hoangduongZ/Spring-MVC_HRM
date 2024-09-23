package fa.training.service.impl;

import fa.training.dto.account.AccountDto;
import fa.training.entity.Account;
import fa.training.mapper.account.AccountMapper;
import fa.training.repository.AccountRepository;
import fa.training.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDto save(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);
    }

    public boolean existsByAccount(String account) {
        return accountRepository.findByAccount(account) != null;
    }

    public boolean existsByEmail(String email) {
        return accountRepository.findByEmail(email) != null;
    }
}
