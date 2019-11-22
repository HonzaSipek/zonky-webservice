package com.zonky.homework.dto;

import lombok.Value;

import java.util.List;

@Value
public class LoanSummary {

    private final Float minInterestRate;
    private final Float maxInterestRate;
    private final Float avgInterestRate;
    private final List<Loan> loans;
}
