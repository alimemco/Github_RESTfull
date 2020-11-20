package com.learn.github.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.learn.github.database.dao.UserDao;
import com.learn.github.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
//@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {


    private static volatile MyDatabase instance;

    public static MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, MyDatabase.class, "db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }



    // --- DAO ---
    public abstract UserDao userDao();
}
