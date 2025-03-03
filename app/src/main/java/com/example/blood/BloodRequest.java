package com.example.blood;

public class BloodRequest {
    private String name, bloodGroup, phone, address, reason;

    // Empty constructor required for Firestore
    public BloodRequest() {}

    public BloodRequest(String name, String bloodGroup, String phone, String address, String reason) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.address = address;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getReason() {
        return reason;
    }
}
