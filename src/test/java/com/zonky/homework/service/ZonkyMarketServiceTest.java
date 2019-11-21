package com.zonky.homework.service;

import com.zonky.homework.dto.Loan;
import com.zonky.homework.exception.ZonkyDataRetrievalException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ZonkyMarketServiceTest {

    private static final String HEADER_PAGE_INDEX_NAME = "X-Page";
    private static final int HEADER_PAGE_INDEX_VALUE = 0;
    private static final String HEADER_PAGE_SIZE_NAME = "X-Size";
    private static final int HEADER_PAGE_SIZE_VALUE = 5;
    private static final String HEADER_PAGE_ORDER_NAME = "X-Order";
    private static final String HEADER_PAGE_ORDER_VALUE = "-datePublished";
    private static final int LOANS_COUNT = 5;

    @Test
    public void buildHeaders_whenAllArgumentsAreCorrect_shouldReturnHeaders() throws Exception {

        ZonkyMarketService service = PowerMockito.mock(ZonkyMarketService.class);
        PowerMockito.doCallRealMethod().when(service, "buildHeaders", Mockito.anyInt(), Mockito.anyString());
        HttpHeaders headers = Whitebox.invokeMethod(service, "buildHeaders", HEADER_PAGE_SIZE_VALUE, HEADER_PAGE_ORDER_VALUE);
        Assert.assertNotNull(headers);
        Assert.assertEquals(String.valueOf(HEADER_PAGE_INDEX_VALUE), headers.get(HEADER_PAGE_INDEX_NAME).get(0));
        Assert.assertEquals(String.valueOf(HEADER_PAGE_SIZE_VALUE), headers.get(HEADER_PAGE_SIZE_NAME).get(0));
        Assert.assertEquals(HEADER_PAGE_ORDER_VALUE, headers.get(HEADER_PAGE_ORDER_NAME).get(0));
    }

    @Test
    public void getLastLoans_whenEverythingIsOk_shouldReturnResponse() throws ZonkyDataRetrievalException {

        ZonkyMarketService service = new ZonkyMarketService();
        List<Loan> loans = service.getLastLoans(LOANS_COUNT);
        Assert.assertNotNull(loans);
    }

    @Test(expected = ZonkyDataRetrievalException.class)
    public void getLastLoans_whenZonkyDoesNotReturnOkStatus_shouldThrowException() throws ZonkyDataRetrievalException {

        ZonkyMarketService service = Mockito.mock(ZonkyMarketService.class);
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.<ParameterizedTypeReference<List<Loan>>>any(),
                Mockito.anyMap())).thenReturn(response);
        ReflectionTestUtils.setField(service, "restTemplate", restTemplate);
        Mockito.doCallRealMethod().when(service).getLastLoans(Mockito.anyInt());
        service.getLastLoans(HEADER_PAGE_SIZE_VALUE);
    }

    @Test(expected = ZonkyDataRetrievalException.class)
    public void getLastLoans_whenConnectionToZonkyFailed_shouldThrowException() throws ZonkyDataRetrievalException {

        ZonkyMarketService service = Mockito.mock(ZonkyMarketService.class);
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.<ParameterizedTypeReference<List<Loan>>>any(),
                Mockito.anyMap())).thenThrow(RestClientException.class);
        ReflectionTestUtils.setField(service, "restTemplate", restTemplate);
        Mockito.doCallRealMethod().when(service).getLastLoans(Mockito.anyInt());
        service.getLastLoans(HEADER_PAGE_SIZE_VALUE);
    }
}