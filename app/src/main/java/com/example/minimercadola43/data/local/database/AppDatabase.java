package com.example.minimercadola43.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.minimercadola43.data.local.dao.ProductDao;
import com.example.minimercadola43.data.local.entity.ProductEntity;
import com.example.minimercadola43.data.local.dao.ProductDao;
import com.example.minimercadola43.data.local.entity.ProductEntity;

@Database(entities = {ProductEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "minimercado_db";
    private static AppDatabase instance;

    public abstract ProductDao productDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
