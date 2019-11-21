package com.zonky.homework.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class LoanTest {

    private static final Integer ID = 610930;
    private static final String URL = "https://app.zonky.cz/loan/610930";
    private static final String NAME = "Zaplacení nevyhodných pujček";
    private static final String STORY = "Chci si udělat pořádek v penězích, a potřebuji pomocod Zonky";
    private static final String PURPOSE = "REFINANCING";
    private static final List<Photo> PHOTOS = Arrays.asList(new Photo("refinancing-1-dd4828d8a8ef3522f794e8498dc6bd6a.jpg", "/loans/610930/photos/76843"));
    private static final String NICK_NAME = "zonky149191";
    private static final Integer TERM_IN_MONTH = 84;
    private static final Float INTEREST_RATE = 0.0599f;
    private static final Float REVENUE_RATE = 0.0499f;
    private static final Integer ANNUITY_WITH_INSURANCE = 7214;
    private static final String RATING = "AAA";
    private static final Boolean TOPPED = false;
    private static final Integer AMOUNT = 445000;
    private static final String COUNTRY_OF_ORIGIN = "CZ";
    private static final String CURRENCY = "CZK";
    private static final Integer REMAINING_INVESTMENT = 367700;
    private static final Integer RESERVED_AMOUNT = 13400;
    private static final Float INVESTMENT_RATE = 0.17370786516853934f;
    private static final Boolean COVERED = false;
    private static final String DATE_PUBLISHED = "2019-11-20T21:27:26.430+01:00";
    private static final Boolean PUBLISHED = true;
    private static final String DEADLINE = "2019-11-22T21:26:34.675+01:00";
    private static final Integer INVESTMENTS_COUNT = 5;
    private static final Integer QUESTIONS_COUNT = 0;
    private static final String REGION = "11";
    private static final String MAIN_INCOME_TYPE = "EMPLOYMENT";
    private static final Boolean INSURANCE_ACTIVE = true;
    private static final List<InsuranceHistoryItem> INSURANCE_HISTORY = Arrays.asList(new InsuranceHistoryItem("2019-11-21", "2026-11-17"));

    @Test
    public void constructor_whenAllRequiredArgumentsAreCorrect_shouldCreateInstance() {

        Loan loan = new Loan(ID, URL, NAME, STORY, PURPOSE, PHOTOS, NICK_NAME, TERM_IN_MONTH, INTEREST_RATE, REVENUE_RATE,
                ANNUITY_WITH_INSURANCE, RATING, TOPPED, AMOUNT, COUNTRY_OF_ORIGIN, CURRENCY, REMAINING_INVESTMENT,
                RESERVED_AMOUNT, INVESTMENT_RATE, COVERED, DATE_PUBLISHED, PUBLISHED, DEADLINE, INVESTMENTS_COUNT,
                QUESTIONS_COUNT, REGION, MAIN_INCOME_TYPE, INSURANCE_ACTIVE, INSURANCE_HISTORY);
        Assert.assertNotNull(loan);
        Assert.assertEquals(ID, loan.getId());
        Assert.assertEquals(URL, loan.getUrl());
        Assert.assertEquals(NAME, loan.getName());
        Assert.assertEquals(STORY, loan.getStory());
        Assert.assertEquals(PURPOSE, loan.getPurpose());
        Assert.assertNotNull(PHOTOS);
        Assert.assertEquals(PHOTOS.get(0).getName(), loan.getPhotos().get(0).getName());
        Assert.assertEquals(PHOTOS.get(0).getUrl(), loan.getPhotos().get(0).getUrl());
        Assert.assertEquals(NICK_NAME, loan.getNickName());
        Assert.assertEquals(TERM_IN_MONTH, loan.getTermInMonths());
        Assert.assertEquals(INTEREST_RATE, loan.getInterestRate());
        Assert.assertEquals(REVENUE_RATE, loan.getRevenueRate());
        Assert.assertEquals(ANNUITY_WITH_INSURANCE, loan.getAnnuityWithInsurance());
        Assert.assertEquals(RATING, loan.getRating());
        Assert.assertEquals(TOPPED, loan.getTopped());
        Assert.assertEquals(AMOUNT, loan.getAmount());
        Assert.assertEquals(COUNTRY_OF_ORIGIN, loan.getCountryOfOrigin());
        Assert.assertEquals(CURRENCY, loan.getCurrency());
        Assert.assertEquals(REMAINING_INVESTMENT, loan.getRemainingInvestment());
        Assert.assertEquals(RESERVED_AMOUNT, loan.getReservedAmount());
        Assert.assertEquals(INVESTMENT_RATE, loan.getInvestmentRate());
        Assert.assertEquals(COVERED, loan.getCovered());
        Assert.assertEquals(DATE_PUBLISHED, loan.getDatePublished());
        Assert.assertEquals(PUBLISHED, loan.getPublished());
        Assert.assertEquals(DEADLINE, loan.getDeadline());
        Assert.assertEquals(INVESTMENTS_COUNT, loan.getInvestmentsCount());
        Assert.assertEquals(QUESTIONS_COUNT, loan.getQuestionsCount());
        Assert.assertEquals(REGION, loan.getRegion());
        Assert.assertEquals(MAIN_INCOME_TYPE, loan.getMainIncomeType());
        Assert.assertEquals(INSURANCE_ACTIVE, loan.getInsuranceActive());
        Assert.assertNotNull(INSURANCE_HISTORY);
        Assert.assertEquals(INSURANCE_HISTORY.get(0).getPolicyPeriodFrom(), loan.getInsuranceHistory().get(0).getPolicyPeriodFrom());
        Assert.assertEquals(INSURANCE_HISTORY.get(0).getPolicyPeriodTo(), loan.getInsuranceHistory().get(0).getPolicyPeriodTo());
    }

    @Test(expected = NullPointerException.class)
    public void constructor_whenRequiredArgumentIsNull_shouldThrowException() {

        new Loan(null, URL, NAME, STORY, PURPOSE, PHOTOS, NICK_NAME, TERM_IN_MONTH, INTEREST_RATE, REVENUE_RATE,
                ANNUITY_WITH_INSURANCE, RATING, TOPPED, AMOUNT, COUNTRY_OF_ORIGIN, CURRENCY, REMAINING_INVESTMENT,
                RESERVED_AMOUNT, INVESTMENT_RATE, COVERED, DATE_PUBLISHED, PUBLISHED, DEADLINE, INVESTMENTS_COUNT,
                QUESTIONS_COUNT, REGION, MAIN_INCOME_TYPE, INSURANCE_ACTIVE, INSURANCE_HISTORY);
    }

    @Test
    public void deserializeJsonToObject_whenAllRequiredFieldsAreDefined_shouldReturnObject() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/json/loan_real_response.json");
        ObjectMapper mapper = new ObjectMapper();
        Loan loan = mapper.readValue(inputStream, Loan.class);
        Assert.assertNotNull(loan);
        Assert.assertEquals(ID, loan.getId());
        Assert.assertEquals(URL, loan.getUrl());
        Assert.assertEquals(NAME, loan.getName());
        Assert.assertEquals(STORY, loan.getStory());
        Assert.assertEquals(PURPOSE, loan.getPurpose());
        Assert.assertNotNull(PHOTOS);
        Assert.assertEquals(PHOTOS.get(0).getName(), loan.getPhotos().get(0).getName());
        Assert.assertEquals(PHOTOS.get(0).getUrl(), loan.getPhotos().get(0).getUrl());
        Assert.assertEquals(NICK_NAME, loan.getNickName());
        Assert.assertEquals(TERM_IN_MONTH, loan.getTermInMonths());
        Assert.assertEquals(INTEREST_RATE, loan.getInterestRate());
        Assert.assertEquals(REVENUE_RATE, loan.getRevenueRate());
        Assert.assertEquals(ANNUITY_WITH_INSURANCE, loan.getAnnuityWithInsurance());
        Assert.assertEquals(RATING, loan.getRating());
        Assert.assertEquals(TOPPED, loan.getTopped());
        Assert.assertEquals(AMOUNT, loan.getAmount());
        Assert.assertEquals(COUNTRY_OF_ORIGIN, loan.getCountryOfOrigin());
        Assert.assertEquals(CURRENCY, loan.getCurrency());
        Assert.assertEquals(REMAINING_INVESTMENT, loan.getRemainingInvestment());
        Assert.assertEquals(RESERVED_AMOUNT, loan.getReservedAmount());
        Assert.assertEquals(INVESTMENT_RATE, loan.getInvestmentRate());
        Assert.assertEquals(COVERED, loan.getCovered());
        Assert.assertEquals(DATE_PUBLISHED, loan.getDatePublished());
        Assert.assertEquals(PUBLISHED, loan.getPublished());
        Assert.assertEquals(DEADLINE, loan.getDeadline());
        Assert.assertEquals(INVESTMENTS_COUNT, loan.getInvestmentsCount());
        Assert.assertEquals(QUESTIONS_COUNT, loan.getQuestionsCount());
        Assert.assertEquals(REGION, loan.getRegion());
        Assert.assertEquals(MAIN_INCOME_TYPE, loan.getMainIncomeType());
        Assert.assertEquals(INSURANCE_ACTIVE, loan.getInsuranceActive());
        Assert.assertNotNull(INSURANCE_HISTORY);
        Assert.assertEquals(INSURANCE_HISTORY.get(0).getPolicyPeriodFrom(), loan.getInsuranceHistory().get(0).getPolicyPeriodFrom());
        Assert.assertEquals(INSURANCE_HISTORY.get(0).getPolicyPeriodTo(), loan.getInsuranceHistory().get(0).getPolicyPeriodTo());
    }

    @Test(expected = ValueInstantiationException.class)
    public void deserializeJsonToObject_whenRequiredFiledIsNotDefined_shouldThrowException() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/json/loan_without_required_field_response.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(inputStream, Loan.class);
    }
}