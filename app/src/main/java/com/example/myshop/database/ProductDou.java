package com.example.myshop.database;

import android.icu.text.Replaceable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myshop.model.Product;

import java.util.List;
//Dao for Products
@Dao
public interface ProductDou {
    @Query("select * from Product")
    List<Product> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Query("select sum(quantity) from product")
    int getSumQuantity();
}
