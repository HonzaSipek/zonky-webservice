package com.zonky.homework.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class PhotoTest {

    private static final String NAME = "refinancing-1-dd4828d8a8ef3522f794e8498dc6bd6a.jpg";
    private static final String URL = "/loans/610930/photos/76843";

    @Test
    public void constructor_whenAllRequiredArgumentsAreCorrect_shouldCreateInstance() {

        Photo photo = new Photo(NAME, URL);
        Assert.assertNotNull(photo);
        Assert.assertEquals(NAME, photo.getName());
        Assert.assertEquals(URL, photo.getUrl());
    }

    @Test(expected = NullPointerException.class)
    public void constructor_whenRequiredArgumentIsNull_shouldThrowException() {

        new Photo(NAME, null);
    }

    @Test
    public void deserializeJsonToObject_whenAllRequiredFieldsAreDefined_shouldReturnObject() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/json/photo_real_response.json");
        ObjectMapper mapper = new ObjectMapper();
        Photo photo = mapper.readValue(inputStream, Photo.class);
        Assert.assertNotNull(photo);
        Assert.assertEquals(NAME, photo.getName());
        Assert.assertEquals(URL, photo.getUrl());
    }

    @Test(expected = ValueInstantiationException.class)
    public void deserializeJsonToObject_whenRequiredFiledIsNotDefined_shouldThrowException() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/json/photo_no_fields_response.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(inputStream, Photo.class);
    }
}