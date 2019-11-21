package com.zonky.homework.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan implements Serializable {

    private final Integer id;
    private final String url;
    private final String name;
    private final String story;
    private final String purpose;
    private final List<Photo> photos;
    private final String nickName;
    private final Integer termInMonths;
    private final Float interestRate;
    private final Float revenueRate;
    private final Integer annuityWithInsurance;
    private final String rating;
    private final Boolean topped;
    private final Integer amount;
    private final String countryOfOrigin;
    private final String currency;
    private final Integer remainingInvestment;
    private final Integer reservedAmount;
    private final Float investmentRate;
    private final Boolean covered;
    private final String datePublished;
    private final Boolean published;
    private final String deadline;
    private final Integer investmentsCount;
    private final Integer questionsCount;
    private final String region;
    private final String mainIncomeType;
    private final Boolean insuranceActive;
    private final List<InsuranceHistoryItem> insuranceHistory;

    @JsonCreator
    public Loan(
            @NonNull @JsonProperty("id") Integer id,
            @JsonProperty("url") String url,
            @NonNull @JsonProperty("name") String name,
            @JsonProperty("story") String story,
            @NonNull @JsonProperty("purpose") String purpose,
            @JsonProperty("photos") List<Photo> photos,
            @NonNull @JsonProperty("nickName") String nickName,
            @NonNull @JsonProperty("termInMonths") Integer termInMonths,
            @NonNull @JsonProperty("interestRate") Float interestRate,
            @NonNull @JsonProperty("revenueRate") Float revenueRate,
            @NonNull @JsonProperty("annuityWithInsurance") Integer annuityWithInsurance,
            @NonNull @JsonProperty("rating") String rating,
            @JsonProperty("topped") Boolean topped,
            @NonNull @JsonProperty("amount") Integer amount,
            @NonNull @JsonProperty("countryOfOrigin") String countryOfOrigin,
            @NonNull @JsonProperty("currency") String currency,
            @NonNull @JsonProperty("remainingInvestment") Integer remainingInvestment,
            @NonNull @JsonProperty("reservedAmount") Integer reservedAmount,
            @NonNull @JsonProperty("investmentRate") Float investmentRate,
            @NonNull @JsonProperty("covered") Boolean covered,
            @NonNull @JsonProperty("datePublished") String datePublished,
            @NonNull @JsonProperty("published") Boolean published,
            @NonNull @JsonProperty("deadline") String deadline,
            @NonNull @JsonProperty("investmentsCount") Integer investmentsCount,
            @NonNull @JsonProperty("questionsCount") Integer questionsCount,
            @NonNull @JsonProperty("region") String region,
            @NonNull @JsonProperty("mainIncomeType") String mainIncomeType,
            @NonNull @JsonProperty("insuranceActive") Boolean insuranceActive,
            @NonNull @JsonProperty("insuranceHistory") List<InsuranceHistoryItem> insuranceHistory) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.story = story;
        this.purpose = purpose;
        this.photos = photos;
        this.nickName = nickName;
        this.termInMonths = termInMonths;
        this.interestRate = interestRate;
        this.revenueRate = revenueRate;
        this.annuityWithInsurance = annuityWithInsurance;
        this.rating = rating;
        this.topped = topped;
        this.amount = amount;
        this.countryOfOrigin = countryOfOrigin;
        this.currency = currency;
        this.remainingInvestment = remainingInvestment;
        this.reservedAmount = reservedAmount;
        this.investmentRate = investmentRate;
        this.covered = covered;
        this.datePublished = datePublished;
        this.published = published;
        this.deadline = deadline;
        this.investmentsCount = investmentsCount;
        this.questionsCount = questionsCount;
        this.region = region;
        this.mainIncomeType = mainIncomeType;
        this.insuranceActive = insuranceActive;
        this.insuranceHistory = insuranceHistory;
    }
}
