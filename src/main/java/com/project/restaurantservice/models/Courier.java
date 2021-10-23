package com.project.restaurantservice.models;

import javax.persistence.*;

@Table(name="couriers")
@Entity
public class Courier {

    @Id
    @SequenceGenerator(
            name = "courier_sequence",
            sequenceName = "courier_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courier_sequence"
    )
    private Long courierId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="area_zip")
    private String areaZip;

    protected Courier() { }

    public Courier(String firstName, String lastName, String areaZip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.areaZip = areaZip;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAreaZip() {
        return areaZip;
    }

    public void setAreaZip(String areaZip) {
        this.areaZip = areaZip;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "courierId=" + courierId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", areaZip='" + areaZip + '\'' +
                '}';
    }
}
