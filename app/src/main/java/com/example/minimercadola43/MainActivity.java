package com.example.minimercadola43;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.minimercadola43.databinding.ActivityMainBinding;
import com.example.minimercadola43.ui.inventory.InventoryFragment;
import com.example.minimercadola43.ui.product.add.AddProductFragment;
import com.example.minimercadola43.ui.product.update.UpdateProductFragment;
import com.example.minimercadola43.ui.sales.SalesFragment;

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
