package com.example.minimercadola43.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.minimercadola43.data.local.dao.ProductDao;
import com.example.minimercadola43.data.local.database.AppDatabase;
import com.example.minimercadola43.data.local.entity.ProductEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<ProductEntity>> allProducts;
    private ExecutorService executorService;

    public ProductRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        productDao = database.productDao();
        allProducts = productDao.getAllProducts();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<ProductEntity>> getAllProducts() {
        return allProducts;
    }

    public LiveData<ProductEntity> getProductById(long id) {
        return productDao.getProductById(id);
    }

    public void insertProduct(final ProductEntity product) {
        executorService.execute(() -> {
            productDao.insertProduct(product);
        });
    }

    public void updateProduct(final ProductEntity product) {
        executorService.execute(() -> {
            productDao.updateProduct(product);
        });
    }

    public void addStock(final long productId, final double amount) {
        executorService.execute(() -> {
            productDao.addStock(productId, amount);
        });
    }

    public void reduceStock(final long productId, final double amount) {
        executorService.execute(() -> {
            productDao.reduceStock(productId, amount);
        });
    }
}
