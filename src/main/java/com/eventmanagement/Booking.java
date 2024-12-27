package com.eventmanagement;

import java.io.Serializable;

public class Booking implements Serializable {
    private static final long serialVersionUID = 1L; // For version control of serialized data
    private String name;
    private String date;
    private String contact;
    private String category;
    private String location;
    private String capacity;
    private String organizer;
    private String amount;

    public Booking(String name, String date, String contact, String category, String location, String capacity, String organizer, String amount) {
        this.name = name;
        this.date = date;
        this.contact = contact;
        this.category = category;
        this.location = location;
        this.capacity = capacity;
        this.organizer = organizer;
        this.amount = amount;
    }

    public String getDetails() {
        return String.format("%s | %s | %s | %s | %s", name, date, contact, category, amount);
    }
}
