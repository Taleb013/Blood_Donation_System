package com.example.blood.extra;

public class Donor {
    private String phone, dob, weight, address, gender, bloodGroup;

    // Empty constructor required for Firestore
    public Donor() {
    }

    public Donor(String phone, String dob, String weight, String address, String gender, String bloodGroup) {
        this.phone = phone;
        this.dob = dob;
        this.weight = weight;
        this.address = address;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
    }

    // Getters
    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getWeight() {
        return weight;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
}
