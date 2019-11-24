package com.lendico.test.model;

import java.time.LocalDate;

public class Installment {

    double borrowerPaymentAmount;
    LocalDate date;
    double initialOutstandingPrincipal;
    double interest;
    double principal;
    double remainingOutstandingPrincipal;

    public Installment(double borrowerPaymentAmount,
                       LocalDate date,
                       double initialOutstandingPrincipal,
                       double interest,
                       double principal,
                       double remainingOutstandingPrincipal) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        this.date = date;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.interest = interest;
        this.principal = principal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    public double getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public double getInterest() {
        return interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }
}
