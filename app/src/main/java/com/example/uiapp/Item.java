package com.example.uiapp;

public class Item {
    int image;
    String date;

    public Item(int image, String date) {

        this.image = image;
        this.date = date;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDate(){return date;}

    public void setDate(String date){
        this.date = date;
    }
}
