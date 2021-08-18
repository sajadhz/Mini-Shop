package com.example.myshop.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myshop.model.Product;
//Database
@Database(entities = Product.class,exportSchema = false,version = 1)
public abstract class ShopDatabase extends RoomDatabase {
    public static final String DB_NAME = "database";
    static ShopDatabase instance;
    public static synchronized ShopDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context
                    ,ShopDatabase.class
                    ,DB_NAME
            ).fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract ProductDou productDou();
}
