package com.zonky.homework.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo implements Serializable {

    private final String name;
    private final String url;

    @JsonCreator
    public Photo(
            @NonNull @JsonProperty("name") String name,
            @NonNull @JsonProperty("url") String url) {
        this.name = name;
        this.url = url;
    }
}
