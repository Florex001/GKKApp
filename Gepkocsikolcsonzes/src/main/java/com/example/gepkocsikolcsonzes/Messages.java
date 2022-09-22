package com.example.gepkocsikolcsonzes;

public class Messages {

    String id;
    String user_name;
    String name;
    String message;
    String status;

   public Messages(String id, String user_name, String name, String message, String status){
       this.id = id;
       this.user_name = user_name;
       this.name = name;
       this.message = message;
       this.status = status;
   }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
