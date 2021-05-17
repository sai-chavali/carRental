package com.java.core.mapper;

import com.java.core.UserBooking;

import java.time.LocalDateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBookingMapper implements ResultSetMapper<UserBooking> {

    @Override
    public UserBooking map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new UserBooking(r.getInt("car_id"),
                r.getString("name"),
                r.getString("model"),
                r.getBigDecimal("price"),
                r.getTimestamp("booked_at").toLocalDateTime(),
                r.getTimestamp("start_time").toLocalDateTime(),
                r.getTimestamp("end_time").toLocalDateTime()
        );
    }
}
