package fa.training.service.impl;

import fa.training.dto.account.AccountDto;
import fa.training.entity.Account;
import fa.training.mapper.account.AccountMapper;
import fa.training.repository.AccountRepository;
import fa.training.repository.RoleRepository;
import fa.training.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountDto save(AccountDto accountDto) {
        if (accountDto == null || accountDto.getPassword() == null || accountDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("AccountDto or password must not be null or empty");
        }

        Account account = AccountMapper.toEntity(accountDto);

        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);
    }

    public boolean existsByAccount(String account) {
        return accountRepository.findByAccount(account).orElse(null) != null;
    }

    public boolean existsByEmail(String email) {
        return accountRepository.findByEmail(email) != null;
    }

}
