package com.java.db;

import com.java.core.User;
import com.java.core.mapper.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("select * from car_rent.user")
    List<User> getAllUsers();

    @SqlQuery("select * from car_rent.user where email=:email and password=:password")
    User findByEmailPassword(@Bind("email") String email, @Bind("password") String password);

    @SqlQuery("select * from car_rent.user where id = :id")
    User findById(@Bind("id") Long id);

    @SqlQuery("select * from car_rent.user where email=:email")
    User findByEmail(@Bind("email") String email);

    @SqlUpdate("INSERT INTO car_rent.\"user\"(email, username, password, status) VALUES (:email, :username,:password, :status)")
    int insert(@BindBean User user);

    @SqlUpdate("update car_rent.\"user\" set email = :email, password = :password, status = :status where id=:id")
    int update(@BindBean User user);
}
