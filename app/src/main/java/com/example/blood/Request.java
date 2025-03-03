package com.example.blood;

public class Request {
    private String name, phone, location, reason, bloodGroup;

    // Default constructor required for Firebase
    public Request() {
    }

    public Request(String name, String phone, String location, String reason, String bloodGroup) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.reason = reason;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getReason() {
        return reason;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
}
