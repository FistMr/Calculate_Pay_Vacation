package com.pavelpuchkow.calculate.calculate_pay_vacation.controller;

import com.pavelpuchkow.calculate.calculate_pay_vacation.dto.CalendarPeriod;
import com.pavelpuchkow.calculate.calculate_pay_vacation.exception.IncorrectValueException;
import com.pavelpuchkow.calculate.calculate_pay_vacation.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SalaryCalculationController {

    private final CalendarService calendarService;

    @GetMapping("/calculate")
    public String calculateSalary(@RequestParam("salary") Double avgSalaryYear,
                                  @RequestParam("days") Optional<Integer> numberDaysOfVacation,
                                  @RequestParam("start") Optional<String> startDate,
                                  @RequestParam("end") Optional<String> endDate) {
        LocalDate start, end;
        Double avgSalaryDay = avgSalaryYear / 247;
        double avgPayVacation;

        if (startDate.isPresent() && endDate.isPresent()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            start = LocalDate.parse(startDate.get(), formatter);
            end = LocalDate.parse(endDate.get(), formatter);

            if (start.isAfter(end)) {
                throw new IncorrectValueException("Incorrect Date");
            }

            CalendarPeriod period = calendarService.getPeriod(startDate.get() + "-" + endDate.get());
            Integer worksDay = period.getStatistic().getWork_days();
            avgPayVacation = avgSalaryDay * worksDay;

        } else {

            if (numberDaysOfVacation.isEmpty()) {
                throw new IncorrectValueException("Incorrect number of vacation days");
            }

            avgPayVacation = avgSalaryDay * numberDaysOfVacation.get();
        }
        return Double.toString(avgPayVacation);

    }

}
