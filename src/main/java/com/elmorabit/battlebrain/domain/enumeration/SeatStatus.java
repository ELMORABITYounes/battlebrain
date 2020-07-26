package com.elmorabit.battlebrain.domain.enumeration;

/**
 * The SeatStatus enumeration.
 */
public enum SeatStatus {
    BOOKED("Booked"),
    AVAILABLE("Available"),
    UNSET("Unset");

    private final String value;


    SeatStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
