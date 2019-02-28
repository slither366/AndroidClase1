package com.example.retrofitclase.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.retrofitclase.R;
import com.example.retrofitclase.entity.Categoria;
import com.example.retrofitclase.rest.HelperWs;
import com.example.retrofitclase.rest.MethodWs;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_obtener_todas, btn_obtener_una, btn_grabar_categoria, btn_eliminar_categoria, btn_actualizar_categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarUI();

        iniciarEventos();

    }

    private void iniciarEventos() {

        //TODO El Todo sirve para tener cosas pendientes por hacer y en Futuro revisarlo
        btn_obtener_todas.setOnClickListener(this);
        btn_obtener_una.setOnClickListener(this);
        btn_grabar_categoria.setOnClickListener(this);
        btn_actualizar_categoria.setOnClickListener(this);
        btn_eliminar_categoria.setOnClickListener(this);

    }

    private void inicializarUI() {

        btn_obtener_todas = findViewById(R.id.btn_obtener_todas);
        btn_obtener_una = findViewById(R.id.btn_obtener_una);
        btn_grabar_categoria = findViewById(R.id.btn_grabar_categoria);
        btn_actualizar_categoria = findViewById(R.id.btn_actualizar_categoria);
        btn_eliminar_categoria = findViewById(R.id.btn_eliminar_categoria);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_obtener_todas:

                llamarWsObtenerTodos();

                break;

            case R.id.btn_obtener_una:

                break;

            case R.id.btn_grabar_categoria:

                break;

            case R.id.btn_actualizar_categoria:

                break;

            case R.id.btn_eliminar_categoria:

                break;

        }



    }

    private void llamarWsObtenerTodos() {

        MethodWs methodWs = HelperWs.getConfiguration().create(MethodWs.class);
        Call<ArrayList<Categoria>> response =methodWs.obtenerTodosCategorias();

        response.enqueue(new Callback<ArrayList<Categoria>>() {
            @Override
            public void onResponse(Call<ArrayList<Categoria>> call, Response<ArrayList<Categoria>> response) {

                if(response.isSuccessful()){
                    ArrayList<Categoria> list_Categoria = response.body();

                    for (int i=0; i<list_Categoria.size(); i++){
                        Log.i("dflores", list_Categoria.get(i).get_id());
                        Log.i("dflores", list_Categoria.get(i).getNombre());
                        Log.i("dflores", list_Categoria.get(i).getDescripcion());
                        Log.i("dflores", "" + list_Categoria.get(i).get__v());
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Categoria>> call, Throwable t) {

                Log.e("dflores", t.getMessage());

            }
        });

    }
}
