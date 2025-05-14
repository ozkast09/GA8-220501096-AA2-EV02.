package com.example.minimercadola43.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "quantity")
    private double quantity;

    @ColumnInfo(name = "unit")
    private String unit;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "brand")
    private String brand;

    @ColumnInfo(name = "supplier")
    private String supplier;

    // Constructor, getters y setters
    public ProductEntity(String name, double quantity, String unit, String location, String brand, String supplier) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.location = location;
        this.brand = brand;
        this.supplier = supplier;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupplier() {
        return supplier;
    }

}