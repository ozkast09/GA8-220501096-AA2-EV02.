package com.example.minimercadola43.ui.sales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.minimercadola43.R;
import com.example.minimercadola43.data.local.entity.ProductEntity;
import com.example.minimercadola43.ui.product.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class SalesFragment extends Fragment {

    private Spinner productSpinner;
    private EditText quantityEditText;
    private Button registerSaleButton;
    private ProductViewModel viewModel;
    private List<ProductEntity> productList = new ArrayList<>();
    private ProductEntity selectedProduct;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);

        // Inicializar vistas
        productSpinner = view.findViewById(R.id.spinnerProduct);
        quantityEditText = view.findViewById(R.id.editTextQuantityToRemove);
        registerSaleButton = view.findViewById(R.id.btnRegisterSale);

        // Obtener ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        // Configurar el adaptador del spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, new ArrayList<>());
        productSpinner.setAdapter(adapter);

        // Observar la lista de productos para popular el spinner
        viewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            productList = products;
            List<String> productNames = new ArrayList<>();
            for (ProductEntity product : products) {
                productNames.add(product.getName());
            }

            ArrayAdapter<String> updatedAdapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_dropdown_item, productNames);
            productSpinner.setAdapter(updatedAdapter);
        });

        // Listener para el spinner
        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < productList.size()) {
                    selectedProduct = productList.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedProduct = null;
            }
        });

        // Listener para el botón de registrar salida
        registerSaleButton.setOnClickListener(v -> registerProductSale());

        return view;
    }

    private void registerProductSale() {
        if (selectedProduct == null) {
            Toast.makeText(requireContext(), "Por favor, seleccione un producto",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String quantityStr = quantityEditText.getText().toString().trim();
        if (quantityStr.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, ingrese la cantidad a retirar",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double quantityToRemove = Double.parseDouble(quantityStr);

            if (quantityToRemove <= 0) {
                Toast.makeText(requireContext(), "La cantidad debe ser mayor que 0",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (quantityToRemove > selectedProduct.getQuantity()) {
                Toast.makeText(requireContext(),
                        "No hay suficiente stock disponible. Stock actual: " + selectedProduct.getQuantity(),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Llamar al ViewModel para registrar la salida
            viewModel.reduceStock(selectedProduct.getId(), quantityToRemove);

            Toast.makeText(requireContext(), "Salida registrada correctamente",
                    Toast.LENGTH_SHORT).show();

            // Limpiar el campo de cantidad
            quantityEditText.setText("");

        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Por favor, ingrese una cantidad válida",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
