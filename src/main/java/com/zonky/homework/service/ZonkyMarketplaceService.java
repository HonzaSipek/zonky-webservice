package com.zonky.homework.service;

import com.zonky.homework.dto.Loan;
import com.zonky.homework.exception.ZonkyDataRetrievalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * Zonky marketplace service provides an interface to Zonky marketplace REST API.
 */
@Service
public class ZonkyMarketplaceService {

    private static final Logger LOG = LoggerFactory.getLogger(ZonkyMarketplaceService.class);
    private static final String ENDPOINT_MARKETPLACE = "https://api.zonky.cz/loans/marketplace";
    private static final String SORTING_DESC_PARAMETER_DATE_PUBLISHED = "-datePublished";
    private static final ParameterizedTypeReference LOANS_REF_TYPE = new ParameterizedTypeReference<List<Loan>>() {
    };
    private RestTemplate restTemplate;

    public ZonkyMarketplaceService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Gets last loans from marketplace ordered by publish date
     *
     * @param lastLoanCount count of last loans
     * @return loans from marketplace
     */
    public List<Loan> getLastLoans(int lastLoanCount) {

        LOG.info("Sending request for loans to Zonky: {}", ENDPOINT_MARKETPLACE);
        try {
            ResponseEntity<List<Loan>> response = restTemplate.exchange(
                    ENDPOINT_MARKETPLACE,
                    HttpMethod.GET,
                    new HttpEntity<>(buildHeaders(lastLoanCount, SORTING_DESC_PARAMETER_DATE_PUBLISHED)),
                    LOANS_REF_TYPE,
                    new HashMap<>());
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                String msg = String.format("Unable to get data from Zonky, status code: %d, body: %s", response.getStatusCodeValue(), response.getBody());
                LOG.error(msg);
                throw new ZonkyDataRetrievalException(msg);
            }
            LOG.debug("Response body from Zonky, body: {}", response.getBody());
            LOG.info("Receiving response from Zonky, status code: {}, ", response.getStatusCode());
            return response.getBody();
        } catch (RestClientException ex) {
            String msg = String.format("Unable to get data response from Zonky: %s", ex.getMessage());
            LOG.error(msg);
            throw new ZonkyDataRetrievalException(msg, ex);
        }
    }

    /**
     * Builds request headers (pagination, sorting).
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
