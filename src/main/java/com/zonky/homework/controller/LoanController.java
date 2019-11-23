package com.zonky.homework.controller;

import com.zonky.homework.dto.LoanSummary;
import com.zonky.homework.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Loan controller provides method to obtain last loans from Zonky marketplace
 * with additional statistic information (min, max, avg of interest rate).
 */
@RestController
@RequestMapping(value = "/loans")
public class LoanController {

    private static final Logger LOG = LoggerFactory.getLogger(LoanController.class);
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Gets last loans with statistics (min, max, avg of interest rate).
     *
     * @param count count of last loans
     * @return last loans with statistics
     */
    @GetMapping("")
    public LoanSummary getLastLoans(@RequestParam(name = "count", required = false, defaultValue = "100") Integer count) {

        LOG.info("GetLastLoans request: required loans count = {}", count);
        LoanSummary loanSummary = loanService.getLoansSummary(count);
        LOG.debug("GetLastLoans response body: loans with statistics = {}", loanSummary);
        LOG.info("GetLastLoans response overview: count of retrieved loans = {}", loanSummary.getLoans().size());
        return loanSummary;
    }
}
