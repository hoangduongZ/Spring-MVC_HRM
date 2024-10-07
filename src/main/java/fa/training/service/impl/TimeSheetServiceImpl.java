package fa.training.service.impl;

import fa.training.entity.Account;
import fa.training.entity.TimeSheet;
import fa.training.entity.WorkDays;
import fa.training.repository.AccountRepository;
import fa.training.repository.EmployeeRepository;
import fa.training.repository.TimeSheetRepository;
import fa.training.repository.WorkDaysRepository;
import fa.training.service.TimeSheetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TimeSheetServiceImpl implements TimeSheetService {
    private TimeSheetRepository timeSheetRepository;
    private AccountRepository accountRepository;
    private WorkDaysRepository workDaysRepository;

    @Override
    public void checkIn(String accountName) {
        Account existAccount = accountRepository.findByAccount(accountName).orElseThrow(
                () -> new IllegalStateException("Account not exist")
        );
        Optional<TimeSheet> existTimeSheet = timeSheetRepository.findByAccountAndDate(existAccount, LocalDate.now());

        if (existTimeSheet.isPresent()) {
            throw new IllegalStateException("Already checked in for today");
        }

        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setCheckInTime(LocalDateTime.now());
        timeSheet.setDate(LocalDate.now());
        timeSheet.setAccount(existAccount);
        timeSheetRepository.save(timeSheet);
        updateWorkDays(existAccount);
    }

    @Override
    public void checkOut(String accountName) {
        Account existAccount = accountRepository.findByAccount(accountName).orElseThrow(
                () -> new IllegalStateException("Account not exist")
        );

        Optional<TimeSheet> existTimeSheetOptional = timeSheetRepository.findByAccountAndDate(existAccount, LocalDate.now());

        if (existTimeSheetOptional.isEmpty()) {
            throw new IllegalStateException("You don't have check-in today");
        }

        TimeSheet existTimeSheet = existTimeSheetOptional.get();
        existTimeSheet.setCheckOutTime(LocalDateTime.now());
        timeSheetRepository.save(existTimeSheet);
    }

    private void updateWorkDays(Account account) {
        WorkDays workDays = workDaysRepository.findByAccount(account).orElse(new WorkDays());

        if (workDays.getLastUpdated() == null || !workDays.getLastUpdated().isEqual(LocalDate.now())) {
            workDays.setTotalDays(workDays.getTotalDays() + 1);
            workDays.setLastUpdated(LocalDate.now());
        }

        workDays.setAccount(account);
        workDaysRepository.save(workDays);
    }

    @Override
    public List<TimeSheet> getTimeSheetDetailInMonth(String accountName, int month, int year) {
        Account account = accountRepository.findByAccount(accountName).orElseThrow(
                () -> new IllegalStateException("Account not exist")
        );

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return timeSheetRepository.findByAccountAndDateBetween(account, startDate, endDate);
    }

}
