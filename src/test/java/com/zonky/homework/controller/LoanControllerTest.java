package com.zonky.homework.controller;

import com.zonky.homework.dto.LoanSummary;
import com.zonky.homework.service.LoanService;
import com.zonky.homework.service.ZonkyMarketService;
import org.assertj.core.util.Maps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getLastLoans_whenRealZonkyServiceReturnSomething_shouldReturnSummary() {

        ResponseEntity<LoanSummary> loanSummaryResponse = restTemplate.getForEntity("/loans", LoanSummary.class, Maps.newHashMap("count", "3"));
        Assert.assertNotNull(loanSummaryResponse);
        Assert.assertEquals(HttpStatus.OK, loanSummaryResponse.getStatusCode());
    }

    @Test
    public void getLastLoans_whenMockedZonkyServiceThrowsException_shouldReturnServerError() {

        ZonkyMarketService zonkyMarketService = Mockito.mock(ZonkyMarketService.class);
        Mockito.when(zonkyMarketService.getLastLoans(Mockito.anyInt())).thenThrow(RestClientException.class);
        ReflectionTestUtils.setField(loanService, "zonkyMarketService", zonkyMarketService);
        ResponseEntity<LoanSummary> loanSummaryResponse = restTemplate.getForEntity("/loans", LoanSummary.class, Maps.newHashMap("count", "3"));
        Assert.assertNotNull(loanSummaryResponse);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, loanSummaryResponse.getStatusCode());
    }

}