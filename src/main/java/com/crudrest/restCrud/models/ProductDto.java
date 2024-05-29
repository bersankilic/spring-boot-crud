package com.crudrest.restCrud.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The brand is required")
    private String brand;
    
    public @NotEmpty(message = "The name is required") String getName() {
        return name;
    }
    
    public void setName(@NotEmpty(message = "The name is required") String name) {
        this.name = name;
    }
    
    public @NotEmpty(message = "The brand is required") String getBrand() {
        return brand;
    }
    
    public void setBrand(@NotEmpty(message = "The brand is required") String brand) {
        this.brand = brand;
    }
    
    public @NotEmpty(message = "The name is required") String getCategory() {
        return category;
    }
    
    public void setCategory(@NotEmpty(message = "The name is required") String category) {
        this.category = category;
    }
    
    @Min(0)
    public double getPrice() {
        return price;
    }
    
    public void setPrice(@Min(0) double price) {
        this.price = price;
    }
    
    public @Size(min = 10, message = "açıklama en az 10 karakter olmalı") @Size(max = 2000, message = "açıklama 2000 karakteri geçemez") String getDescription() {
        return description;
    }
    
    public void setDescription(@Size(min = 10, message = "açıklama en az 10 karakter olmalı") @Size(max = 2000, message = "açıklama 2000 karakteri geçemez") String description) {
        this.description = description;
    }
    
    public MultipartFile getImageFile() {
        return imageFile;
    }
    
    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
    
    @NotEmpty(message = "İsimlendirme gerekli !")
    private String category;
    @Min(0)
    private double price;
    @Size(min = 10, message = "açıklama en az 10 karakter olmalı")
    @Size(max = 2000, message = "açıklama 2000 karakteri geçemez")
    private String description;
    private MultipartFile imageFile;
}
