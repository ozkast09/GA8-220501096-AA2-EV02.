package com.example.minimercadola43.ui.product.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Arrays;
import java.util.List;

public class AddProductFragment extends Fragment {

    private EditText nameEditText, quantityEditText, newUnitEditText, newLocationEditText,
            newBrandEditText, newSupplierEditText;
    private Spinner unitSpinner, locationSpinner, brandSpinner, supplierSpinner;
    private Button createUnitButton, createLocationButton, createBrandButton,
            createSupplierButton, createProductButton;
    private ProductViewModel viewModel;

    // Listas para los spinners
    private List<String> unitsList = new ArrayList<>();
    private List<String> locationsList = new ArrayList<>();
    private List<String> brandsList = new ArrayList<>();
    private List<String> suppliersList = new ArrayList<>();

    // Adaptadores para los spinners
    private ArrayAdapter<String> unitAdapter;
    private ArrayAdapter<String> locationAdapter;
    private ArrayAdapter<String> brandAdapter;
    private ArrayAdapter<String> supplierAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        // Inicializar ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        // Inicializar vistas - EditTexts
        nameEditText = view.findViewById(R.id.editTextProductName);
        quantityEditText = view.findViewById(R.id.editTextInitialQuantity);
        newUnitEditText = view.findViewById(R.id.editTextNewUnit);
        newLocationEditText = view.findViewById(R.id.editTextNewLocation);
        newBrandEditText = view.findViewById(R.id.editTextNewBrand);
        newSupplierEditText = view.findViewById(R.id.editTextNewSupplier);

        // Inicializar vistas - Spinners
        unitSpinner = view.findViewById(R.id.spinnerUnit);
        locationSpinner = view.findViewById(R.id.spinnerLocation);
        brandSpinner = view.findViewById(R.id.spinnerBrand);
        supplierSpinner = view.findViewById(R.id.spinnerSupplier);

        // Inicializar vistas - Botones
        createUnitButton = view.findViewById(R.id.btnCreateUnit);
        createLocationButton = view.findViewById(R.id.btnCreateLocation);
        createBrandButton = view.findViewById(R.id.btnCreateBrand);
        createSupplierButton = view.findViewById(R.id.btnCreateSupplier);
        createProductButton = view.findViewById(R.id.btnCreateProduct);

        // Inicializar listas predeterminadas para cada spinner
        initDefaultData();

        // Configurar adaptadores para los spinners
        setupSpinnerAdapters();

        // Configurar listeners para los botones de creación
        setupCreateButtonsListeners();

        // Configurar listener para el botón de crear producto
        createProductButton.setOnClickListener(v -> createNewProduct());

        return view;
    }

    private void initDefaultData() {
        // Datos predeterminados para unidades según la imagen
        unitsList.addAll(Arrays.asList("kilo", "libra", "unidad", "mililitro", "litro"));

        // Datos predeterminados para ubicaciones
        locationsList.addAll(Arrays.asList("estante b1", "estante b2", "Estante A1", "Cuarto frío 1"));

        // Datos predeterminados para marcas
        brandsList.addAll(Arrays.asList("Diana", "Alpina", "Roa", "Marca Nueva"));

        // Datos predeterminados para proveedores
        suppliersList.addAll(Arrays.asList("Diana", "Alpina", "Roa", "Proveedor Nuevo"));
    }

    private void setupSpinnerAdapters() {
        // Configurar adaptador para spinner de unidades
        unitAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, unitsList);
        unitSpinner.setAdapter(unitAdapter);

        // Configurar adaptador para spinner de ubicaciones
        locationAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, locationsList);
        locationSpinner.setAdapter(locationAdapter);

        // Configurar adaptador para spinner de marcas
        brandAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, brandsList);
        brandSpinner.setAdapter(brandAdapter);

        // Configurar adaptador para spinner de proveedores
        supplierAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, suppliersList);
        supplierSpinner.setAdapter(supplierAdapter);
    }

    private void setupCreateButtonsListeners() {
        // Botón para crear nueva unidad
        createUnitButton.setOnClickListener(v -> {
            String newUnit = newUnitEditText.getText().toString().trim();
            if (!newUnit.isEmpty() && !unitsList.contains(newUnit)) {
                unitsList.add(newUnit);
                unitAdapter.notifyDataSetChanged();
                unitSpinner.setSelection(unitsList.size() - 1);
                newUnitEditText.setText("");
                Toast.makeText(requireContext(), "Unidad añadida correctamente",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Ingrese una unidad válida",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para crear nueva ubicación
        createLocationButton.setOnClickListener(v -> {
            String newLocation = newLocationEditText.getText().toString().trim();
            if (!newLocation.isEmpty() && !locationsList.contains(newLocation)) {
                locationsList.add(newLocation);
                locationAdapter.notifyDataSetChanged();
                locationSpinner.setSelection(locationsList.size() - 1);
                newLocationEditText.setText("");
                Toast.makeText(requireContext(), "Ubicación añadida correctamente",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Ingrese una ubicación válida",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para crear nueva marca
        createBrandButton.setOnClickListener(v -> {
            String newBrand = newBrandEditText.getText().toString().trim();
            if (!newBrand.isEmpty() && !brandsList.contains(newBrand)) {
                brandsList.add(newBrand);
                brandAdapter.notifyDataSetChanged();
                brandSpinner.setSelection(brandsList.size() - 1);
                newBrandEditText.setText("");
                Toast.makeText(requireContext(), "Marca añadida correctamente",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Ingrese una marca válida",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para crear nuevo proveedor
        createSupplierButton.setOnClickListener(v -> {
            String newSupplier = newSupplierEditText.getText().toString().trim();
            if (!newSupplier.isEmpty() && !suppliersList.contains(newSupplier)) {
                suppliersList.add(newSupplier);
                supplierAdapter.notifyDataSetChanged();
                supplierSpinner.setSelection(suppliersList.size() - 1);
                newSupplierEditText.setText("");
                Toast.makeText(requireContext(), "Proveedor añadido correctamente",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Ingrese un proveedor válido",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createNewProduct() {
        // Obtener valores de los campos
        String name = nameEditText.getText().toString().trim();
        String quantityStr = quantityEditText.getText().toString().trim();

        // Obtener valores seleccionados de spinners
        String unit = unitSpinner.getSelectedItem() != null ?
                unitSpinner.getSelectedItem().toString() : "";
        String location = locationSpinner.getSelectedItem() != null ?
                locationSpinner.getSelectedItem().toString() : "";
        String brand = brandSpinner.getSelectedItem() != null ?
                brandSpinner.getSelectedItem().toString() : "";
        String supplier = supplierSpinner.getSelectedItem() != null ?
                supplierSpinner.getSelectedItem().toString() : "";

        // Validar campos obligatorios
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor ingrese el nombre del producto",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantityStr.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor ingrese la cantidad inicial",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double quantity = Double.parseDouble(quantityStr);
            if (quantity <= 0) {
                Toast.makeText(requireContext(), "La cantidad debe ser mayor que 0",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear un nuevo objeto ProductEntity
            ProductEntity newProduct = new ProductEntity(name, quantity, unit, location, brand, supplier);

            // Guardar el producto en la base de datos
            viewModel.insertProduct(newProduct);

            // Mostrar mensaje de éxito
            Toast.makeText(requireContext(), "Producto creado correctamente",
                    Toast.LENGTH_SHORT).show();

            // Limpiar los campos
            nameEditText.setText("");
            quantityEditText.setText("");

        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Por favor ingrese una cantidad válida",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
