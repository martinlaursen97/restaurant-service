package com.project.restaurantservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAllActiveProducts();
    }

    public void removeProductById(Long productId) {
        System.out.println(productId);
        productRepository.setProductInactive(productId);
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

    public List<Product> getProducts(String[] data) {
        List<Product> products = new ArrayList<>();
        for (String d : data) {
            Long id = Long.parseLong(d);
            Product product = productRepository.findById2(id);
            products.add(product);
        }
        return products;
    }

    public void addNewProduct(String name, Double price, String url, String description) {
        Product product = new Product(name, price, url, description, 1);
        productRepository.save(product);
    }

    public Double getTotal(List<Product> chosenProducts) {
        Double total = 0.0;
        for (Product p : chosenProducts) {
            total += p.getPrice();
        }
        return total;
    }

    public List<Product> getProductsById(List<Long> productIds) {
        List<Product> products = new ArrayList<>();
        for (Long id : productIds) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(
                            "id: " + id + ", does not exist.")
                    );
            products.add(product);
        }
        return products;
    }

    public String[] getProductNames(String[] data) {
        String[] names = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            String d = data[i];
            Long id = Long.parseLong(d);
            names[i] = productRepository.getProductName(id);
        }

        return names;
    }
}
