package com.java.core.mapper;


import com.java.core.User;
import com.java.core.UserStatus;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {

    public User map(int index, ResultSet rs, StatementContext sc) throws SQLException {
        return new User(rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                UserStatus.valueOfLabel(rs.getString("status")));
    }
}