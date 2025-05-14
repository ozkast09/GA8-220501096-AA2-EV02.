package com.minimercadola43.app.ui.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minimercadola43.app.R;
import com.minimercadola43.app.data.local.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductEntity> products = new ArrayList<>();

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductEntity currentProduct = products.get(position);
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewQuantity.setText(String.valueOf(currentProduct.getQuantity()));
        holder.textViewUnit.setText(currentProduct.getUnit());
        holder.textViewLocation.setText(currentProduct.getLocation());
        holder.textViewBrand.setText(currentProduct.getBrand());
        holder.textViewSupplier.setText(currentProduct.getSupplier());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
        notifyDataSetChanged();
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
