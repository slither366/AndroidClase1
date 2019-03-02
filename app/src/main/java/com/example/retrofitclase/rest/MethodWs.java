package com.example.retrofitclase.rest;

import com.example.retrofitclase.entity.Categoria;
import com.example.retrofitclase.entity.Request.CategoriaRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MethodWs {

    @GET("categoria")
    @Headers("Content-Type: application/json")
    Call<ArrayList<Categoria>> obtenerTodosCategorias();

    @GET("categoria/{idCategoria}")
    @Headers("Content-Type: application/json")
    Call<Categoria> obtenerUnaCategoria(@Path("idCategoria") String idCategoria);

    @POST("categoria")
    @Headers("Content-Type: application/json")
    Call<ArrayList<Categoria>> grabarCategoria(@Body CategoriaRequest categoriaRequest);

    @PUT("categoria")
    @Headers("Content-Type: application/json")
    Call<ArrayList<Categoria>> actualizarCategoria(@Body CategoriaRequest categoriaRequest);

}
