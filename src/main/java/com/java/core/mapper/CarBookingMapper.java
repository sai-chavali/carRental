package com.java.core.mapper;

import com.java.core.CarBooking;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarBookingMapper implements ResultSetMapper<CarBooking> {

    @Override
    public CarBooking map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new CarBooking(r.getLong("id"),
                r.getString("username"),
                r.getString("email"),
                r.getTimestamp("start_time").toLocalDateTime(),
                r.getTimestamp("end_time").toLocalDateTime()
        );
    }
}
