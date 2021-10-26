package com.project.restaurantservice.models;

import javax.persistence.*;

@Table(name="users")
@Entity
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    private String email;
    private String street;
    private String city;
    private String zip;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="user_role")
    private Long userRole;

    public User() { }

    public User(String username,
                String password,
                String email,
                String street,
                String city,
                String zip,
                String phoneNumber,
                Long userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserRole() {
        return userRole;
    }

    public void setUserRole(Long userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + userId +
                ", firstName='" + username + '\'' +
                ", lastName='" + password + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
