package com.java.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserBooking {

    @NotNull
    @JsonProperty
    private int carId;

    @NotNull
    @JsonProperty
    private String name;

    @NotNull
    @JsonProperty
    private String model;

    @NotNull
    @JsonProperty
    private BigDecimal price;

    @NotNull
    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime booked_at;

    @NotNull
    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start_time;

    @NotNull
    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end_time;

    public UserBooking(@NotNull int carId, @NotNull String name, @NotNull String model, @NotNull BigDecimal price, @NotNull LocalDateTime booked_at, @NotNull LocalDateTime start_time, @NotNull LocalDateTime end_time) {
        this.carId = carId;
        this.name = name;
        this.model = model;
        this.price = price;
        this.booked_at = booked_at;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
