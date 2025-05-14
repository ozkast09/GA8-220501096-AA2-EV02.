package com.example.minimercadola43.ui.product;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.minimercadola43.data.local.entity.ProductEntity;
import com.example.minimercadola43.data.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;
    private LiveData<List<ProductEntity>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
    }

    public LiveData<List<ProductEntity>> getAllProducts() {
        return allProducts;
    }

    public LiveData<ProductEntity> getProductById(long id) {
        return repository.getProductById(id);
    }

    public void insertProduct(ProductEntity product) {
        repository.insertProduct(product);
    }

    public void updateProduct(ProductEntity product) {
        repository.updateProduct(product);
    }

    public void addStock(long productId, double amount) {
        repository.addStock(productId, amount);
    }

    public void reduceStock(long productId, double amount) {
        repository.reduceStock(productId, amount);
    }
}
