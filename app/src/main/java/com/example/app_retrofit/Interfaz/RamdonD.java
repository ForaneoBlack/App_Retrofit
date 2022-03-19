package com.example.app_retrofit.Interfaz;

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.app_retrofit.modelo.ramdonD;

import java.util.List;

public interface RamdonD {

    @GET("posts")
    Call<List<ramdonD>> getRamdons();

}
