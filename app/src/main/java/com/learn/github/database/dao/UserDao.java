package com.learn.github.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.learn.github.model.User;

import java.util.Date;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by Philippe on 02/03/2018.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE login = :userLogin")
    LiveData<User> load(String userLogin);

    @Query("SELECT * FROM user WHERE login = :userLogin LIMIT 1")
    User hasUser(String userLogin);
}