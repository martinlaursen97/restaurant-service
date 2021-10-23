package com.project.restaurantservice.models;

import javax.persistence.*;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long orderNumber;

    @Column(name="user_id")
    private Long customerId;

    @Column(name="courier_id")
    private Long courierId;

    @Column(name="to_street")
    private String toStreet;

    @Column(name="to_city")
    private String toCity;

    @Column(name="to_zip")
    private String toZip;

    @Column(name="order_date")
    private String orderDate;


    protected Order() {}

    public Order(Long customerId,
                 Long courierId,
                 String toStreet,
                 String toCity,
                 String toZip,
                 String orderDate) {
        this.customerId = customerId;
        this.courierId = courierId;
        this.toStreet = toStreet;
        this.toCity = toCity;
        this.toZip = toZip;
        this.orderDate = orderDate;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public String getToStreet() {
        return toStreet;
    }

    public void setToStreet(String toStreet) {
        this.toStreet = toStreet;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToZip() {
        return toZip;
    }

    public void setToZip(String toZip) {
        this.toZip = toZip;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customerId=" + customerId +
                ", courierId=" + courierId +
                ", toStreet='" + toStreet + '\'' +
                ", toCity='" + toCity + '\'' +
                ", toZip='" + toZip + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
