package com.example.retrofitktphanesikriptoparauygulamas;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface kriptoAPI {
    //GET, POST, UPDATE, DELETE

    //URL BASE -> www.website.com
    // GET -> price?key=xxx

    //https://api.nomics.com/v1/
    // prices?key=2187154b76945f2373394aa34f7dc98a
    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json


    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Observable<List<kriptomodel>> getData();
   // Call<List<kriptomodel>> getData();

}
