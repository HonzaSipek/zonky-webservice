package com.zonky.homework.exception;

/**
 * Data retrieval exception is generated if retrieving data from Zonky failed.
 */
public class ZonkyDataRetrievalException extends Exception {

    public ZonkyDataRetrievalException(String message) {
        super(message);
    }

    public ZonkyDataRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
