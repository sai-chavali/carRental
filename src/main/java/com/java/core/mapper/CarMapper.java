package com.java.core.mapper;

import com.java.core.Car;
import com.java.core.CarAvailabilityStatus;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements ResultSetMapper<Car> {

    public Car map(int index, ResultSet rs, StatementContext sc) throws SQLException {
        return new Car(rs.getInt("car_id"),
                rs.getString("name"),
                rs.getString("model"),
                rs.getInt("manufacturing_year"),
                rs.getString("color"),
                rs.getBigDecimal("price"),
                CarAvailabilityStatus.valueOf(rs.getString("status")));
    }
}
