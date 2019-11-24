package com.lendico.test.services;

import com.lendico.test.model.Installment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepaymentService {

    private double truncate(double val) {
        val *= 1000;
        val = Math.floor(val);
        double temp = Math.floor(val/10)*10;
        temp = val - temp;
        val /= 10;
        val = Math.floor(val);
        if (temp >= 5)
            val = val+1;
        val /= 100;
        return val;
    }

    private double getOrdinaryAnnuity(double loanAmount,
                                      double nominalRate,
                                      int duration) {
        double denom = Math.pow(1+nominalRate, -duration);
        denom = 1 - denom;
        double numer = nominalRate*loanAmount;
        return truncate(numer/denom);
    }

    public List<Installment> getInstallments(double loanAmount,
                                             double nominalRate,
                                             int duration,
                                             LocalDate startDate) {
        ArrayList<Installment> ret = new ArrayList<>();
        nominalRate /= 1200 ;
        double annuity = getOrdinaryAnnuity(loanAmount, nominalRate, duration);
        double oustanding_balance = loanAmount;
        for(int i = 0 ; i < duration ; i++) {
            double interest = truncate(nominalRate*oustanding_balance);
            double borrowerPaymentAmount = (oustanding_balance+interest) > annuity ? annuity : oustanding_balance+interest;
            LocalDate date = startDate.plusMonths(i);
            double initialOutstandingPrincipal = oustanding_balance;
            double principal = truncate(borrowerPaymentAmount - interest);
            oustanding_balance = truncate(oustanding_balance - principal);

            ret.add(new Installment(borrowerPaymentAmount,
                                    date,
                                    initialOutstandingPrincipal,
                                    interest, principal,
                                    oustanding_balance));
        }
        return ret;
    }
}
