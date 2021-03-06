package com.example.moneymanager.DBConnection;

public class Entry {
    String email;
    String date;
    String item_bought;
    String ammount_received;
    String ammount_spent;

    public String getItem_bought() {
        return item_bought;
    }

    public void setItem_bought(String item_bought) {
        this.item_bought = item_bought;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmmount_received() {
        return ammount_received;
    }

    public void setAmmount_received(String ammount_received) {
        this.ammount_received = ammount_received;
    }

    public String getAmmount_spent() {
        return ammount_spent;
    }

    public void setAmmount_spent(String ammount_spent) {
        this.ammount_spent = ammount_spent;
    }
}
