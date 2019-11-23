package com.zonky.homework.service;

import com.zonky.homework.dto.Loan;
import com.zonky.homework.dto.LoanSummary;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

public class LoanServiceTest {

    private static final float LOAN_A_INTEREST_RATE = 1.1000f;
    private static final float LOAN_B_INTEREST_RATE = 3.3000f;
    private static final float LOAN_C_INTEREST_RATE = 5.5000f;
    private static final float LOANS_AVG_INTEREST_RATE = 3.3000f;

    @Test
    public void getLoansSummary_whenMockedZonkyServiceReturnsSomeLoans_shouldReturnSummaryContainingLoansAndStatistics() {

        ZonkyMarketplaceService zonkyMarketplaceService = Mockito.mock(ZonkyMarketplaceService.class);
        Mockito.when(zonkyMarketplaceService.getLastLoans(Mockito.anyInt())).thenReturn(
                Arrays.asList(
                        new Loan(1, "URL", "NAME", "STORY", "PURPOSE", new ArrayList(),
                                "NICK_NAME", 1, LOAN_A_INTEREST_RATE, 1f, 1,
                                "RATING", true, 1, "COUNTRY_OF_ORIGIN", "CURRENCY",
                                1, 1, 1f, true, "DATE_PUBLISHED",
                                true, "DEADLINE", 1, 1, "REGION",
                                "MAIN_INCOME_TYPE", true, new ArrayList()),
                        new Loan(1, "URL", "NAME", "STORY", "PURPOSE", new ArrayList(),
                                "NICK_NAME", 1, LOAN_B_INTEREST_RATE, 1f, 1,
                                "RATING", true, 1, "COUNTRY_OF_ORIGIN", "CURRENCY",
                                1, 1, 1f, true, "DATE_PUBLISHED",
                                true, "DEADLINE", 1, 1, "REGION",
                                "MAIN_INCOME_TYPE", true, new ArrayList()),
                        new Loan(1, "URL", "NAME", "STORY", "PURPOSE", new ArrayList(),
                                "NICKNAME", 1, LOAN_C_INTEREST_RATE, 1f, 1,
                                "RATING", true, 1, "COUNTRY_OF_ORIGIN", "CURRENCY",
                                1, 1, 1f, true, "DATE_PUBLISHED",
                                true, "DEADLINE", 1, 1, "REGION",
                                "MAIN_INCOME_TYPE", true, new ArrayList()))
        );
        LoanService loanService = new LoanService(zonkyMarketplaceService);
        LoanSummary loanSummary = loanService.getLoansSummary(3);
        Assert.assertNotNull(loanSummary);
        Assert.assertEquals(3, loanSummary.getLoans().size());
        Assert.assertEquals(LOAN_A_INTEREST_RATE, loanSummary.getMinInterestRate(), 0.0);
        Assert.assertEquals(LOAN_C_INTEREST_RATE, loanSummary.getMaxInterestRate(), 0.0);
        Assert.assertEquals(LOANS_AVG_INTEREST_RATE, loanSummary.getAvgInterestRate(), 0.0);
    }

    @Test
    public void getLoansSummary_whenMockedZonkyServiceDoesNotReturnAnyLoan_shouldReturnEmptySummary() {

        ZonkyMarketplaceService zonkyMarketplaceService = Mockito.mock(ZonkyMarketplaceService.class);
        Mockito.when(zonkyMarketplaceService.getLastLoans(Mockito.anyInt())).thenReturn(new ArrayList<>());
        LoanService loanService = new LoanService(zonkyMarketplaceService);
        LoanSummary loanSummary = loanService.getLoansSummary(3);
        Assert.assertNotNull(loanSummary);
        Assert.assertEquals(0, loanSummary.getLoans().size());
        Assert.assertNull(loanSummary.getMinInterestRate());
        Assert.assertNull(loanSummary.getMaxInterestRate());
        Assert.assertNull(loanSummary.getAvgInterestRate());
    }

    @Test
    public void getLoansSummary_whenZonkyServiceReturnsSomething_shouldReturnSomeSummary() {

        ZonkyMarketplaceService zonkyMarketplaceService = new ZonkyMarketplaceService();
        LoanService loanService = new LoanService(zonkyMarketplaceService);
        LoanSummary loanSummary = loanService.getLoansSummary(3);
        Assert.assertNotNull(loanSummary);
    }
}