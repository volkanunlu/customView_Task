package com.volkanunlu.ecommerceapp.roomdb;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.volkanunlu.ecommerceapp.models.AddAdressEntityModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface AddAddressDao {

    @Query("SELECT * FROM AddAdressEntityModel")
    Flowable<List<AddAdressEntityModel>> getAll();  //hepsini çekme

    @Insert //ekleme işlemi
    Completable insert(AddAdressEntityModel addAdressEntityModel);

    @Delete //silme işlemi
    Completable delete(AddAdressEntityModel addAdressEntityModel);


}
