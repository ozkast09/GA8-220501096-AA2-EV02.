package com.minimercadola43.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.minimercadola43.app.data.local.entity.ProductEntity;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> getAllProducts();

    @Query("SELECT * FROM products WHERE id = :id")
    LiveData<ProductEntity> getProductById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(ProductEntity product);

    @Update
    void updateProduct(ProductEntity product);

    @Query("UPDATE products SET quantity = quantity + :amount WHERE id = :productId")
    void addStock(long productId, double amount);

    @Query("UPDATE products SET quantity = quantity - :amount WHERE id = :productId")
    void reduceStock(long productId, double amount);
}
