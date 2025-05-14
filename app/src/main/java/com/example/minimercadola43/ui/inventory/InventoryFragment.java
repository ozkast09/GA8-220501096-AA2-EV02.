package com.example.minimercadola43.ui.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.minimercadola43.databinding.FragmentInventoryBinding;
import com.example.minimercadola43.ui.product.ProductAdapter;
import com.example.minimercadola43.ui.product.ProductViewModel;

import java.util.ArrayList;

public class InventoryFragment extends Fragment {

    private FragmentInventoryBinding binding;
    private ProductViewModel productViewModel;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInventoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurar RecyclerView
        adapter = new ProductAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);

        // Inicializar ViewModel
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Observar cambios en la lista de productos
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            // Si products es null, pasa una lista vacía; si no, pasa una nueva instancia
            adapter.submitList(products == null ? new ArrayList<>() : new ArrayList<>(products));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
