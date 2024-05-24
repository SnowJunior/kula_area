package com.nyabwana.kula_area;

public class User {
    private String userId;
    private String email;
    private String name;
    private String role;
    private String password;
    private String password_confirm;

    public User(String userId, String email, String name, String role, String password, String password_confirm) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
        this.password = password;
        this.password_confirm = password_confirm;
    }

    public User() {

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

}
