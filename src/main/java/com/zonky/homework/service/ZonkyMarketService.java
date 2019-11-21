package com.zonky.homework.service;

import com.zonky.homework.dto.Loan;
import com.zonky.homework.exception.ZonkyDataRetrievalException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class ZonkyMarketService {

    private static final String ENDPOINT_MARKETPLACE = "https://api.zonky.cz/loans/marketplace";
    private static final String SORTING_DESC_PARAMETER_DATE_PUBLISHED = "-datePublished";
    private static final ParameterizedTypeReference LOANS_REF_TYPE = new ParameterizedTypeReference<List<Loan>>() {
    };
    private RestTemplate restTemplate;

    public ZonkyMarketService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Gets last loans from marketplace ordered by publish date
     *
     * @param lastLoanCount count of last loans
     * @return loans from marketplace
     * @throws ZonkyDataRetrievalException if retrieving data from Zonky failed
     */
    public List<Loan> getLastLoans(int lastLoanCount) throws ZonkyDataRetrievalException {

        try {
            ResponseEntity<List<Loan>> response = restTemplate.exchange(
                    ENDPOINT_MARKETPLACE,
                    HttpMethod.GET,
                    new HttpEntity<>(buildHeaders(lastLoanCount, SORTING_DESC_PARAMETER_DATE_PUBLISHED)),
                    LOANS_REF_TYPE,
                    new HashMap<>());
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                throw new ZonkyDataRetrievalException(String.format("Unable to get data from Zonky, status code: %d", response.getStatusCodeValue()));
            }
            return response.getBody();
        } catch (RestClientException ex) {
            throw new ZonkyDataRetrievalException("Unable to get data response from Zonky", ex);
        }
    }

    /**
     * Build request headers (pagination, sorting).
     *
     * @param pageSize         size of elements on the first page
     * @param sortingParameter name of parameter used for sorting (default: ASC, with "-" prefix: DESC)
     * @return request headers
     */
    private HttpHeaders buildHeaders(int pageSize, String sortingParameter) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Page", "0");
        headers.add("X-Size", String.valueOf(pageSize));
        headers.add("X-Order", sortingParameter);
        return headers;
    }
}
