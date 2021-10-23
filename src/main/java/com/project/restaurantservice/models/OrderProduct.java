package com.project.restaurantservice.models;

import javax.persistence.*;

@Table(name="order_products")
@Entity
public class OrderProduct {

    @Id
    @SequenceGenerator(
            name = "order_product_sequence",
            sequenceName = "order_product_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_product_sequence"
    )
    private Long order_products_id;

    @Column(name="product_id")
    private Long product_id;

    @Column(name="order_number")
    private Long orderNumber;

    protected OrderProduct() { }

    public OrderProduct(Long product_id, Long orderNumber) {
        this.product_id = product_id;
        this.orderNumber = orderNumber;
    }

    public Long getOrder_products_id() {
        return order_products_id;
    }

    public void setOrder_products_id(Long order_products_id) {
        this.order_products_id = order_products_id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "OrderProducts{" +
                "order_products_id=" + order_products_id +
                ", product_id=" + product_id +
                '}';
    }
}
