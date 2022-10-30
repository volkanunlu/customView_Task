package com.volkanunlu.ecommerceapp.models;

import com.google.gson.annotations.SerializedName;

public class AddAddressModel {

    @SerializedName("CustomViewType")
    private String type;

    @SerializedName("Properties")
    private AddAddressProperties properties;

    public AddAddressModel(String type, AddAddressProperties properties) {
        this.type = type;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AddAddressProperties getProperties() {
        return properties;
    }

    public void setProperties(AddAddressProperties properties) {
        this.properties = properties;
    }
}
