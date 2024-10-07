package fa.training.service.impl;

import fa.training.entity.Account;
import fa.training.repository.AccountRepository;
import fa.training.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccount(accountName).orElseThrow(
                ()-> new UsernameNotFoundException("User not found")
        );
        Set<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> "ROLE_" + role.getName())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return new User(account.getAccount(), account.getPassword(), authorities);
    }
}
