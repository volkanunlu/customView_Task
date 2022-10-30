package com.volkanunlu.ecommerceapp.service;

import com.volkanunlu.ecommerceapp.models.AddAddressModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AddAddressAPI {

    //retrofit ile json dosyasını çekeceğim.Bu sayede customview oluşturup bir activityi json dosyasına göre dinamik ui şeklinde oluşturacağım.
    //https://raw.githubusercontent.com/volkanunlu/addressTaskJson/main/dataJson.json

  @GET("volkanunlu/addressTaskJson/main/dataJson.json")
    Call<List<AddAddressModel>>getData();


}
