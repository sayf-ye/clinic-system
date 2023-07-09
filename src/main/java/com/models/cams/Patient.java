/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models.cams;

/**
 *
 * @author User
 */
public class Patient extends User{

    private Address address;

    public Patient() {
    }

    public Patient(Address address, String name, String email, String password, String contact) {
        super(name, email, password, contact);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
