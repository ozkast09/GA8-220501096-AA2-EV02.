package com.minimercadola43.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.minimercadola43.app.databinding.ActivityMainBinding;
import com.minimercadola43.app.ui.inventory.InventoryFragment;
import com.minimercadola43.app.ui.product.add.AddProductFragment;
import com.minimercadola43.app.ui.product.update.UpdateProductFragment;
import com.minimercadola43.app.ui.sales.SalesFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Por defecto muestra el inventario
        loadFragment(new InventoryFragment());

        // Configurar clics de botones
        binding.btnInventory.setOnClickListener(v -> loadFragment(new InventoryFragment()));
        binding.btnUpdateProduct.setOnClickListener(v -> loadFragment(new UpdateProductFragment()));
        binding.btnAddProduct.setOnClickListener(v -> loadFragment(new AddProductFragment()));
        binding.btnRegisterSale.setOnClickListener(v -> loadFragment(new SalesFragment()));
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}
