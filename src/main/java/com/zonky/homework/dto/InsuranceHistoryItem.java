package com.zonky.homework.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

@Getter
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
