package com.example.minimercadola43.ui.product.update;

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

public class UpdateProductFragment extends Fragment {

    private Spinner productSpinner;
    private EditText quantityEditText;
    private Button updateButton;
    private ProductViewModel viewModel;
    private List<ProductEntity> productList = new ArrayList<>();
    private ProductEntity selectedProduct;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_product, container, false);

        productSpinner = view.findViewById(R.id.spinnerProduct);
        quantityEditText = view.findViewById(R.id.editTextQuantity);
        updateButton = view.findViewById(R.id.btnUpdateProduct);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        // Inicializa el adapter aquí, estará vacío hasta que lleguen los datos
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<>());
        productSpinner.setAdapter(adapter);

        // Observa los productos y actualiza la lista y el spinner
        viewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            // Esta línea asegura que productList nunca sea null
            productList = (products != null) ? products : new ArrayList<>();
            List<String> productNames = new ArrayList<>();
            for (ProductEntity product : productList) {
                productNames.add(product.getName());
            }
            adapter.clear();
            adapter.addAll(productNames);
            adapter.notifyDataSetChanged();

            // Asegúrate de que selectedProduct se restablezca si la lista se vacía
            if (productList.isEmpty()) {
                selectedProduct = null;
            } else if (selectedProduct != null && !productList.contains(selectedProduct)) {
                // Si el producto seleccionado ya no está en la lista (p. ej., fue eliminado), resetéalo
                selectedProduct = null;
                // Opcional: Mostrar un mensaje al usuario
                // Toast.makeText(requireContext(), "El producto seleccionado ya no existe", Toast.LENGTH_SHORT).show();
            }
        });

        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Verifica los límites antes de acceder a la lista
                if (!productList.isEmpty() && position >= 0 && position < productList.size()) {
                    selectedProduct = productList.get(position);
                } else {
                    selectedProduct = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedProduct = null;
            }
        });

        updateButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                String quantityStr = quantityEditText.getText().toString().trim();
                if (quantityStr.isEmpty()) {
                    Toast.makeText(requireContext(), "Por favor, ingrese una cantidad", Toast.LENGTH_SHORT).show();
                    return;
                }
                double quantityToAdd;
                try {
                    quantityToAdd = Double.parseDouble(quantityStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Por favor, ingrese una cantidad válida", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (quantityToAdd <= 0) {
                    Toast.makeText(requireContext(), "La cantidad debe ser mayor que 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.addStock(selectedProduct.getId(), quantityToAdd);
                Toast.makeText(requireContext(), "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
                quantityEditText.setText("");
            } else {
                Toast.makeText(requireContext(), "Por favor, seleccione un producto", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}