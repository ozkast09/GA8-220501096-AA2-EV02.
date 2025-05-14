package com.example.minimercadola43.ui.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimercadola43.R;
import com.example.minimercadola43.data.local.entity.ProductEntity;

public class ProductAdapter extends ListAdapter<ProductEntity, ProductAdapter.ProductViewHolder> {

    public ProductAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<ProductEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ProductEntity>() {
                @Override
                public boolean areItemsTheSame(ProductEntity oldItem, ProductEntity newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(ProductEntity oldItem, ProductEntity newItem) {
                    // Comparación de todos los campos relevantes
                    return oldItem.getName().equals(newItem.getName()) &&
                            oldItem.getQuantity() == newItem.getQuantity() &&
                            oldItem.getUnit().equals(newItem.getUnit()) &&
                            oldItem.getLocation().equals(newItem.getLocation()) &&
                            oldItem.getBrand().equals(newItem.getBrand()) &&
                            oldItem.getSupplier().equals(newItem.getSupplier());
                }
            };

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductEntity currentProduct = getItem(position);
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewQuantity.setText(String.valueOf(currentProduct.getQuantity()));
        holder.textViewUnit.setText(currentProduct.getUnit());
        holder.textViewLocation.setText(currentProduct.getLocation());
        holder.textViewBrand.setText(currentProduct.getBrand());
        holder.textViewSupplier.setText(currentProduct.getSupplier());
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewQuantity;
        private TextView textViewUnit;
        private TextView textViewLocation;
        private TextView textViewBrand;
        private TextView textViewSupplier;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewUnit = itemView.findViewById(R.id.textViewUnit);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewBrand = itemView.findViewById(R.id.textViewBrand);
            textViewSupplier = itemView.findViewById(R.id.textViewSupplier);
        }
    }
}

