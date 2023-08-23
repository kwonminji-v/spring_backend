package com.study.jpa;


import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getStreet() { return street; }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

}


