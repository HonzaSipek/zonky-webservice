package com.zonky.homework.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class InsuranceHistoryItemTest {

    private static final String POLICY_PERIOD_FROM = "2019-11-21";
    private static final String POLICY_PERIOD_TO = "2026-11-17";

    @Test
    public void constructor_whenAllRequiredArgumentsAreCorrect_shouldCreateInstance() {

        InsuranceHistoryItem insuranceHistoryItem = new InsuranceHistoryItem(POLICY_PERIOD_FROM, POLICY_PERIOD_TO);
        Assert.assertNotNull(insuranceHistoryItem);
        Assert.assertEquals(POLICY_PERIOD_FROM, insuranceHistoryItem.getPolicyPeriodFrom());
        Assert.assertEquals(POLICY_PERIOD_TO, insuranceHistoryItem.getPolicyPeriodTo());
    }

    @Test(expected = NullPointerException.class)
    public void constructor_whenRequiredArgumentIsNull_shouldThrowException() {

        new InsuranceHistoryItem(POLICY_PERIOD_FROM, null);
    }

    @Test
    public void deserializeJsonToObject_whenAllRequiredFieldsAreDefined_shouldReturnObject() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/json/insurance_history_real_response.json");
        ObjectMapper mapper = new ObjectMapper();
        InsuranceHistoryItem insuranceHistoryItem = mapper.readValue(inputStream, InsuranceHistoryItem.class);
        Assert.assertNotNull(insuranceHistoryItem);
        Assert.assertEquals(POLICY_PERIOD_FROM, insuranceHistoryItem.getPolicyPeriodFrom());
        Assert.assertEquals(POLICY_PERIOD_TO, insuranceHistoryItem.getPolicyPeriodTo());
    }

    @Test(expected = ValueInstantiationException.class)
    public void deserializeJsonToObject_whenRequiredFiledIsNotDefined_shouldThrowException() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/json/insurance_history_no_fields_response.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(inputStream, InsuranceHistoryItem.class);
    }
}