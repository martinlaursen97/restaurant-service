package com.project.restaurantservice.models;

import javax.persistence.*;

@Table(name="products")
@Entity
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="price")
    private Double price;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="active")
    private Integer active;

    public Product() { }

    public Product(String productName, Double price, String imageUrl, String productDescription, Integer active) {
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productDescription = productDescription;
        this.active = active;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
