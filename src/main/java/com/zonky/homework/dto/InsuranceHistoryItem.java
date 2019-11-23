package com.zonky.homework.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;

/**
 * Insurance history item is a part of {@link Loan} DTO.
 */
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsuranceHistoryItem implements Serializable {

    private final String policyPeriodFrom;
    private final String policyPeriodTo;

    @JsonCreator
    public InsuranceHistoryItem(
            @NonNull @JsonProperty("policyPeriodFrom") String policyPeriodFrom,
            @NonNull @JsonProperty("policyPeriodTo") String policyPeriodTo) {
        this.policyPeriodFrom = policyPeriodFrom;
        this.policyPeriodTo = policyPeriodTo;
    }
}
