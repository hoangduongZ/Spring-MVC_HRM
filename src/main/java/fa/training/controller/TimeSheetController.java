package fa.training.controller;

import fa.training.entity.TimeSheet;
import fa.training.service.TimeSheetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/timesheet")
@AllArgsConstructor
public class TimeSheetController {
    TimeSheetService timeSheetService;


    @GetMapping
    public String getTimeSheet() {
        return "timesheet/check-in";
    }

    @PostMapping("check-in")
    public String postTimeSheetCheckIn(@RequestParam String accountName, Model model) {
        try {
            timeSheetService.checkIn(accountName);
            model.addAttribute("message", "Check-in success");
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "timesheet/check-in";
    }

    @PostMapping("check-out")
    public String postTimeSheetCheckOut(@RequestParam String accountName, Model model) {
        try {
            timeSheetService.checkOut(accountName);
            model.addAttribute("message", "Check-out success");
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "timesheet/check-in";
    }

    @GetMapping("/details")
    public String getTimeSheetDetails(Model model,
                                      @RequestParam(required = false) String accountName,
                                      @RequestParam(required = false) Integer month,
                                      @RequestParam(required = false) Integer year) {
        if(accountName != null){
            List<TimeSheet> history = timeSheetService.getTimeSheetDetailInMonth(accountName, month, year);
            model.addAttribute("history", history);
        }
        model.addAttribute("accountName", accountName);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("months", createListInRange(1, 12));
        model.addAttribute("years", createListInRange(2023, 2024));

        return "timesheet/details";
    }


    private List<Integer> createListInRange(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }
        return numbers;
    }

}
