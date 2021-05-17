package com.java.core.DTO;

import com.java.core.CarBooking;
import com.java.core.Car;

import java.util.List;

public class CarBookingsDTO {
    public Car car;
    public List<CarBooking> carBookings;

    public CarBookingsDTO(Car car, List<CarBooking> carBookings) {
        this.car = car;
        this.carBookings = carBookings;
    }
}
