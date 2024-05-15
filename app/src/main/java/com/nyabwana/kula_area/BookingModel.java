package com.nyabwana.kula_area;

import java.util.Date;

public class BookingModel {
    String bookingId;
    String fullName;
    String occasion;
    String phoneNumber;
    String capacity;
    String userId;
    Date pickDate;

    public BookingModel() {
    }
    public BookingModel(String fullName, String occasion, String phoneNumber, String capacity, Date pickDate, String bookingId, String userId) {
        this.fullName = fullName;
        this.occasion = occasion;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.pickDate = pickDate;
        this.bookingId = bookingId;
        this.userId = userId;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Date getPickDate() {
        return pickDate;
    }

    public void setPickDate(Date pickDate) {
        this.pickDate = pickDate;
    }
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
