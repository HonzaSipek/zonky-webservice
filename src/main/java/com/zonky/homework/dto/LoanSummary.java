package com.zonky.homework.dto;

import lombok.Value;

import java.util.List;

/**
 * Loan summary is the main response object of {@link com.zonky.homework.controller.LoanController#getLastLoans(Integer)}
 * and holds all loans retrieved from Zonky martketplace and some statistics about interest rate.
 */
@Value
public class LoanSummary {

    private final Float minInterestRate;
    private final Float maxInterestRate;
    private final Float avgInterestRate;
    private final List<Loan> loans;
}
