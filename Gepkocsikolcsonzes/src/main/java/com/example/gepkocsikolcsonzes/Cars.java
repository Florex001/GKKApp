package com.example.gepkocsikolcsonzes;

public class Cars {

    String id;
    String car;
    String info;
    String daily_price;

    public Cars(String id, String car, String info, String daily_price){
        this.id = id;
        this.car = car;
        this.info = info;
        this.daily_price = daily_price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getDaily_price() {
        return daily_price;
    }

    public String getInfo() {
        return info;
    }

    public void setDaily_price(String daily_price) {
        this.daily_price = daily_price;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
