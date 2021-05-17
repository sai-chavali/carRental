package com.java.db;

import org.skife.jdbi.v2.OutParameters;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlCall;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.OutParameter;

import java.sql.Types;

public interface TokenDAO{

    @SqlQuery("select car_rent.generate_token(:id)")
    String findOrCreateTokenforUser(@Bind("id") Long id);

    @SqlQuery("select user_id from car_rent.session where token = :token")
    Long findTokenForUser(@Bind("token") String token);

    @SqlUpdate("delete from car_rent.session where user_id=:user_id")
    void deleteTokenForUser(@Bind("user_id") Long user_id);
}
