package com.java.core.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BookingTimingsDTO {

    @NotNull
    @JsonProperty
    private LocalDateTime start_time;

    @NotNull
    @JsonProperty
    private LocalDateTime end_time;

    BookingTimingsDTO() {

    }

    public BookingTimingsDTO(@NotNull LocalDateTime start_time, @NotNull LocalDateTime end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }
}
