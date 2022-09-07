package com.example.gepkocsikolcsonzes;

public class Booking {
    String id;
    String user_id;
    String borrowed_vehicle_id;
    String borrow_start;
    String borrow_end;
    String indenty_card_number;
    String price;
    String status;

    public Booking(String id, String user_id, String borrowed_vehicle_id, String borrow_start, String borrow_end, String indenty_card_number, String price, String status ){
        this.id = id;
        this.user_id = user_id;
        this.borrowed_vehicle_id = borrowed_vehicle_id;
        this.borrow_start = borrow_start;
        this.borrow_end = borrow_end;
        this.indenty_card_number = indenty_card_number;
        this.price = price;
        this.status = status;
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

    public String getIndenty_card_number() {
        return indenty_card_number;
    }

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
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

    public void setIndenty_card_number(String identity_card_number) {
        this.indenty_card_number = identity_card_number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
