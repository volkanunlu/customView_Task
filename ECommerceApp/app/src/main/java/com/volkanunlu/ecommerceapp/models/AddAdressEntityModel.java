package com.volkanunlu.ecommerceapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class AddAdressEntityModel implements Serializable {

    @PrimaryKey(autoGenerate = true) //Room database primary key
    public int id;
    //Sırası ile jsondan oluşturduğum viewlerime göre database tarafında columnlar oluşturdum.

    @ColumnInfo(name = "userName")
    public String userName;

    @ColumnInfo(name = "userAddress")
    public String userAddress;

    @ColumnInfo(name= "userCountry")
    public String userCountry;

    @ColumnInfo(name = "userCity")
    public String userCity;

    @ColumnInfo(name = "userDisctrict")
    public String userDisctrict;

    @ColumnInfo(name = "userPostCode")
    public String userPostCode;

    @ColumnInfo(name = "userPhone")
    public String userPhone;

    //Constructor yapısı
    public AddAdressEntityModel(String userName, String userAddress, String userCountry, String userCity, String userDisctrict, String userPostCode, String userPhone) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userCountry = userCountry;
        this.userCity = userCity;
        this.userDisctrict = userDisctrict;
        this.userPostCode = userPostCode;
        this.userPhone = userPhone;
    }

    //Getter Metotlarım
    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public String getUserCity() {
        return userCity;
    }

    public String getUserDisctrict() {
        return userDisctrict;
    }

    public String getUserPostCode() {
        return userPostCode;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
