package com.example.gepkocsikolcsonzes;

public class Users {
    String id;
    String username;
    String name;
    String password;
    String email;
    String phone_number;
    String registration_date;
    String rank;

    public Users(String id, String username, String name,
                 String password, String email, String phone_number, String registration_date, String rank){
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.registration_date = registration_date;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getRank() {
        return rank;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
