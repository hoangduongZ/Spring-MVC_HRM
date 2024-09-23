package fa.training.mapper.account;

import fa.training.dto.account.AccountDto;
import fa.training.entity.Account;
import fa.training.entity.Employee;

public class AccountMapper {

    public static AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto dto = new AccountDto();
        dto.setAccount(account.getAccount());
        dto.setId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setPassword(account.getPassword());
        dto.setStatus(account.getStatus());

        return dto;
    }

    public static Account toEntity(AccountDto dto) {
        if (dto == null) {
            return null;
        }

        Account account = new Account();
        account.setId(dto.getId());
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        account.setAccount(dto.getAccount());
        account.setStatus(dto.getStatus() == null ? 0 : dto.getStatus());
        if(dto.getEmployeeId() != null){
            Employee employee = new Employee();
            employee.setId(dto.getEmployeeId());
            account.setEmployee(employee);
        }

        return account;
    }
}