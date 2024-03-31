package com.pavelpuchkow.calculate.calculate_pay_vacation.service;

import com.pavelpuchkow.calculate.calculate_pay_vacation.dto.CalendarPeriod;
import com.pavelpuchkow.calculate.calculate_pay_vacation.exception.ExternalServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private static final String API_TOKEN = "3fbaafeb0af37af305ff3290cb1b4823";
    private static final String COUNTRY_CODE = "ru";
    private static final String FORMAT = "json";


    private final RestTemplate restTemplate;

    public CalendarPeriod getPeriod(String period) {

        final String url = String.format(
               "https://production-calendar.ru/get-period/%s/%s/%s/%s",
                API_TOKEN, COUNTRY_CODE, period, FORMAT);


        ResponseEntity<CalendarPeriod> calendarPeriodResponseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                CalendarPeriod.class
        );

        if (!calendarPeriodResponseEntity.getStatusCode().is2xxSuccessful()) {
            throw new ExternalServiceException("Server is not available");
        }

        if (calendarPeriodResponseEntity.getBody() == null ||
                calendarPeriodResponseEntity.getBody().getStatistic() == null ||
                calendarPeriodResponseEntity.getBody().getStatistic().getWork_days() == null) {
            throw new ExternalServiceException("Incorrect response from the external server");
        }
        return calendarPeriodResponseEntity.getBody();

    }

}
