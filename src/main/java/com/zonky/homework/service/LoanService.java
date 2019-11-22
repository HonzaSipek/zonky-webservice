package com.zonky.homework.service;

import com.zonky.homework.dto.Loan;
import com.zonky.homework.dto.LoanSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final ZonkyMarketService zonkyMarketService;

    @Autowired
    public LoanService(ZonkyMarketService zonkyMarketService) {
        this.zonkyMarketService = zonkyMarketService;
    }

    /**
     * Gets loans summary (last X loans and statistics: min, max, avg of interest rate)
     *
     * @param lastLoansCount count of last loans
     * @return loans summary
     */
    public LoanSummary getLoansSummary(int lastLoansCount) {

        List<Loan> loans = zonkyMarketService.getLastLoans(lastLoansCount);
        List<Float> interestRates = loans.stream().map(Loan::getInterestRate).collect(Collectors.toList());
        float minInterestRate = (float) interestRates.stream().mapToDouble(Float::doubleValue).min().orElse(Double.NaN);
        float maxInterestRate = (float) interestRates.stream().mapToDouble(Float::doubleValue).max().orElse(Double.NaN);
        float avgInterestRate = (float) interestRates.stream().mapToDouble(Float::doubleValue).average().orElse(Double.NaN);
        return new LoanSummary(
                Float.isNaN(minInterestRate) ? null : minInterestRate,
                Float.isNaN(maxInterestRate) ? null : maxInterestRate,
                Float.isNaN(avgInterestRate) ? null : avgInterestRate,
                loans
        );
    }
}
