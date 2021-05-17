package com.java.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class CarBooking {

    @NotNull
    @JsonProperty
    private Long userId;

    @NotNull
    @JsonProperty
    private String username;

    @NotNull
    @JsonProperty
    private String email;

    @NotNull
    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start_time;

    @NotNull
    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end_time;

    public CarBooking(@NotNull Long userId, @NotNull String username, @NotNull String email, @NotNull LocalDateTime start_time, @NotNull LocalDateTime end_time) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
