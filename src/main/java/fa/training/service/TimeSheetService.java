package fa.training.service;

import fa.training.entity.TimeSheet;

import java.util.List;

public interface TimeSheetService {
    void checkIn(String account);

    void checkOut(String accountName);

    List<TimeSheet> getTimeSheetDetailInMonth(String accountName, int month, int year);
}
