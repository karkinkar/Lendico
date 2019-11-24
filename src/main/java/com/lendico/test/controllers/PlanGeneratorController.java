package com.lendico.test.controllers;

import com.lendico.test.model.Installment;
import com.lendico.test.services.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class PlanGeneratorController {

    @Autowired
    RepaymentService repaymentService;

    private static DateTimeFormatter[] available_formats = {
            DateTimeFormatter.ISO_DATE,
            DateTimeFormatter.BASIC_ISO_DATE,
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.BASIC_ISO_DATE,
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_ORDINAL_DATE,
            DateTimeFormatter.ISO_WEEK_DATE,
            DateTimeFormatter.ISO_OFFSET_DATE
    };
    private LocalDate isParsable(String date, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(date, formatter);
        }
        catch(DateTimeException e) {

        }
        return null;
    }

    private LocalDate parseDateFromString(String date) {
        for(DateTimeFormatter formatter : available_formats) {
            LocalDate date_time = isParsable(date, formatter);
            if (date_time != null) return date_time;
        }
        return null;
    }

    @PostMapping("/generate-plan")
    List<Installment> getPaymentPlan(@RequestParam("loanAmount") double loanAmount,
                                     @RequestParam("nominalRate") double nominalRate,
                                     @RequestParam("duration") int duration,
                                     @RequestParam("startDate") String startDate) {
        if (loanAmount < 0 || nominalRate < 0 || duration <= 0)
            throw new IllegalArgumentException();

        LocalDate date = parseDateFromString(startDate);
        if (date == null)
            throw new IllegalArgumentException();
        return repaymentService.getInstallments(loanAmount, nominalRate, duration, date);
    }
}
