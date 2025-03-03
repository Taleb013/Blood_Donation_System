package com.example.blood.extra;
public class BloodBank {
    private String name, address, contact;

    public BloodBank(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }
}
