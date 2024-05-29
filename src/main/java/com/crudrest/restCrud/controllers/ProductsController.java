package com.crudrest.restCrud.controllers;

import com.crudrest.restCrud.models.Product;
import com.crudrest.restCrud.models.ProductDto;
import com.crudrest.restCrud.services.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsRepository repo;
    
    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("products", products);
        return "products/index";
    }
    
    @GetMapping("create")
    public String showCreatePage(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/CreateProduct";
    }
    
    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ) {
        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDto", "imageFile", "Image file is required"));
        }
        
        if (result.hasErrors()) {
            return "products/CreateProduct";
        }
        
        //veritabanına kaydet 
        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
        
        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storageFileName);
        product.setCategory(productDto.getCategory());
        
        repo.save(product);
        
        return "redirect:/products";
    }
    
    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);
            
            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setBrand(product.getBrand());
            productDto.setPrice(product.getPrice());
            productDto.setCategory(product.getCategory());
            model.addAttribute("productDto", productDto);
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        
        return "products/EditProduct";
    }
    
    @PostMapping("/edit")
    public String editProduct(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ) {
        
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);
            
            if (result.hasErrors()) {
                return "products/EditProduct";
            }
            
            if (!productDto.getImageFile().isEmpty()) {
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
                
                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
                
                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
                
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setBrand(productDto.getBrand());
            product.setPrice(productDto.getPrice());
            product.setCategory(productDto.getCategory());
            repo.save(product);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        
        return "redirect:/products/";
    }
    
    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam int id
    ) {
        
        try {
            Product product = repo.findById(id).get();
            Path imagePath = Paths.get("public/images/" + product.getImageFileName());
            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
            
            repo.delete(product);
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        
        return "redirect:/products";
    }
    
}













