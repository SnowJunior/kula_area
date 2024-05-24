package com.nyabwana.kula_area;

public class Restaurant {
    String name;
    String city;
    String image;
    String cuisine;
    String occasion;
    String phone;
    String price;
    String parking_availability;

    public Restaurant(String name, String city, String cuisine, String occasion, String phone, String price, String parking_availability, String image) {
        this.name = name;
        this.city = city;
        this.cuisine = cuisine;
        this.occasion = occasion;
        this.phone = phone;
        this.price = price;
        this.parking_availability = parking_availability;
        this.image = image;
    }

    public Restaurant() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {this.image = image;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getParking_availability() {
        return parking_availability;
    }

    public void setParking_availability(String parking_availability) {
        this.parking_availability = parking_availability;
    }
}
