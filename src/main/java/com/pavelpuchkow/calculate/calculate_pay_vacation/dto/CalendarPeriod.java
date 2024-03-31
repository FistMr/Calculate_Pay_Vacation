package com.pavelpuchkow.calculate.calculate_pay_vacation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CalendarPeriod {
    private String status;
    private String country_code;
    private String country_text;
    private String dt_start;
    private String dt_end;
    private String work_week_type;
    private String period;
    private String note;
    private List<DayInfo> days;
    private Statistic statistic;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class DayInfo {
        private String date;
        private Integer type_id;
        private String type_text;
        private String week_day;
        private Integer working_hours;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class Statistic {
        private Integer calendar_days;
        private Integer calendar_days_without_holidays;
        private Integer work_days;
        private Integer weekends;
        private Integer holidays;
        private Integer working_hours;

    }


}
