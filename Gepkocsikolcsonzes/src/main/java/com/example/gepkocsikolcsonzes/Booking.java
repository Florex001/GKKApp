package com.example.gepkocsikolcsonzes;

public class Booking {

    int sorszam;
    String id;
    String user_id;
    String borrowed_vehicle_id;
    String borrow_start;
    String borrow_end;
    String driver_license_number;
    String price;

    public Booking(String id, int sorszam, String user_id, String borrowed_vehicle_id, String borrow_start, String borrow_end, String driver_license_number, String price ){
        this.sorszam = sorszam;
        this.id = id;
        this.user_id = user_id;
        this.borrowed_vehicle_id = borrowed_vehicle_id;
        this.borrow_start = borrow_start;
        this.borrow_end = borrow_end;
        this.driver_license_number = driver_license_number;
        this.price = price;
    }

    public void setSorszam(int sorszam) {
        this.sorszam = sorszam;
    }

    public int getSorszam() {
        return sorszam;
    }

    public String getBorrow_end() {
        return borrow_end;
    }

    public String getBorrow_start() {
        return borrow_start;
    }

    public String getBorrowed_vehicle_id() {
        return borrowed_vehicle_id;
    }

    public String getId() {
        return id;
    }

    public String getDriver_license_number() {
        return driver_license_number;
    }

    public String getPrice() {
        return price;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setBorrow_end(String borrow_end) {
        this.borrow_end = borrow_end;
    }

    public void setBorrow_start(String borrow_start) {
        this.borrow_start = borrow_start;
    }

    public void setBorrowed_vehicle_id(String borrowed_vehicle_id) {
        this.borrowed_vehicle_id = borrowed_vehicle_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDriver_license_number(String driver_license_number) {
        this.driver_license_number = driver_license_number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
