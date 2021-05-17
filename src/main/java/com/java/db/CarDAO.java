package com.java.db;

import com.java.core.Car;
import com.java.core.mapper.CarMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(CarMapper.class)
public interface CarDAO {

    @SqlQuery("select * from car_rent.car")
    List<Car> getAll();

    @SqlQuery("select * from car_rent.car where car_id = :id")
    Car findById(@Bind("id") int id);

    @SqlUpdate("delete from car_rent.car where car_id = :id")
    int deleteById(@Bind("id") int id);

    @SqlUpdate("update car_rent.car set name = :name where car_id = :id")
    int update(@BindBean Car car);

    @SqlUpdate("INSERT INTO car_rent.car(name, model, manufacturing_year, color, price, status) VALUES (:name, :model, :made, :color, :price, :status)")
    int insert(@BindBean Car car);
}
