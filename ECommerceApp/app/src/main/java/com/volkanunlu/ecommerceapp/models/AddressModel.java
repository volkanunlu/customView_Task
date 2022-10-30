package com.volkanunlu.ecommerceapp.models;

public class AddressModel {

    //firebase tarafındaki yapı ile isimler aynı olmalı yoksa --> no adapter attached skipping layout hatası alırsın.(recyclerview)

    String address;
    boolean isSelection;

    public AddressModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSelection() {
        return isSelection;
    }

    public void setSelection(boolean selection) {
        isSelection = selection;
    }
}
