package com.java.db;

import com.java.core.CarBooking;
import com.java.core.UserBooking;
import com.java.core.mapper.CarBookingMapper;
import com.java.core.mapper.UserBookingMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingsDAO {
    @Mapper(CarBookingMapper.class)
    @SqlQuery("select id,username,email,start_time,end_time from car_rent.bookings bookings inner join car_rent.user users on bookings.user_id = users.id where car_id = :car_id")
    List<CarBooking> getBookingsForCar(@Bind("car_id") int car_id);

    @Mapper(UserBookingMapper.class)
    @SqlQuery("select car.car_id,name,model,start_time,end_time,bookings.price,booked_at from car_rent.bookings bookings inner join car_rent.car car on bookings.car_id = car.car_id where user_id = :user_id")
    List<UserBooking> getBookingsForUser(@Bind("user_id") Long user_id);

    @SqlUpdate("INSERT INTO car_rent.bookings(car_id, user_id, start_time, end_time, price) VALUES (:car_id,:user_id,:start_time,:end_time, :price)")
    int addBooking(@Bind("car_id") int car_id, @Bind("user_id") Long user_id, @Bind("start_time") LocalDateTime start_time, @Bind("end_time") LocalDateTime end_time, @Bind("price")BigDecimal price);

    @SqlQuery("select count(*) from car_rent.bookings where car_id = :car_id and :start_time BETWEEN start_time and end_time and :end_time BETWEEN start_time and end_time")
    int checkTimingsForCar(@Bind("car_id") int car_id, @Bind("start_time") LocalDateTime start_time, @Bind("end_time") LocalDateTime end_time);
}
