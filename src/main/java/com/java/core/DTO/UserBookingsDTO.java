package com.java.core.DTO;

import com.java.core.User;
import com.java.core.UserBooking;

import java.util.List;

public class UserBookingsDTO {
    public User user;
    public List<UserBooking> userBookings;

    public UserBookingsDTO(User user, List<UserBooking> userBookings) {
        this.user = user;
        this.userBookings = userBookings;
    }
}
