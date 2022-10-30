package com.volkanunlu.ecommerceapp.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.volkanunlu.ecommerceapp.models.AddAdressEntityModel;

@Database(entities = {AddAdressEntityModel.class},version = 1,exportSchema = false)
public abstract class AddAddressDatabase extends RoomDatabase {

    public abstract AddAddressDao addAddressDao();


}
