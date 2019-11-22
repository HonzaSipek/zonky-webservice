package com.zonky.homework.controller;

import com.zonky.homework.dto.LoanSummary;
import com.zonky.homework.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Loan controller provides method to obtain loans from Zonky marketplace
 * with additional statistic information (min, max, avg of interest rate).
 */
@RestController
@RequestMapping(value = "/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("")
    public LoanSummary getLastLoans(@RequestParam(name = "count", required = false, defaultValue = "5") Integer count) {
        return loanService.getLoansSummary(count);
    }
}
