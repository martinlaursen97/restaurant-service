package com.project.restaurantservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void removeProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product getById(Long productId) {
        return productRepository.getById(productId);
    }

    public void saveConfig(Product product) {
        productRepository.save(product);
    }

    public List<Product> searchFor(String keyword) {
        return productRepository.searchFor(keyword);
    }

    public List<Product> getProductsName(String[] data) {
        List<Product> products = new ArrayList<>();
        for (String d : data) {
            Long id = Long.parseLong(d);
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(
                            "id: " + id + ", does not exist.")
                    );
            products.add(product);
        }
        return products;
    }
}
