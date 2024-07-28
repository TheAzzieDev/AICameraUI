package com.example.uiapp;

public class YearItem {
    String year;
    String incidents;
    public YearItem(String year, String incidents){
        this.year = year;
        this.incidents = incidents;
    }
    public String getYear(){
        return year;
    }

    public String getIncidents(){
        return incidents;
    }

}
