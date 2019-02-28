package com.example.retrofitclase.rest;

import com.example.retrofitclase.entity.Categoria;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MethodWs {

    @GET("categoria")
    @Headers("Content-Type: application/json")
    Call<ArrayList<Categoria>> obtenerTodosCategorias();

}
