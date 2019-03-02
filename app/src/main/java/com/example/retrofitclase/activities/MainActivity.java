package com.example.retrofitclase.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.retrofitclase.R;
import com.example.retrofitclase.entity.Categoria;
import com.example.retrofitclase.entity.Request.CategoriaRequest;
import com.example.retrofitclase.rest.HelperWs;
import com.example.retrofitclase.rest.MethodWs;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_obtener_todas, btn_obtener_una, btn_grabar_categoria, btn_eliminar_categoria, btn_actualizar_categoria;
    SweetAlertDialog pd;
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
                
                llamarWsObtenerUnaCategoria();

                break;

            case R.id.btn_grabar_categoria:

                llamarWsGrabarCategoria();

                break;

            case R.id.btn_actualizar_categoria:

                llamarWsActualizarCategoria();

                break;

            case R.id.btn_eliminar_categoria:

                break;

        }

    }

    private void llamarWsActualizarCategoria() {

        CategoriaRequest categoriaRequest = new CategoriaRequest();
        categoriaRequest.setTxt_name_categoria("Categoria DFLORES");
        categoriaRequest.setText_desc_categoria("Descripcion de David Flores plato vegano por favor!(Updated)");
        categoriaRequest.setIdCategoria("5c7aff7767f6890400a64186");

        MethodWs methodWs = HelperWs.getConfiguration().create(MethodWs.class);
        Call<ArrayList<Categoria>> response = methodWs.actualizarCategoria(categoriaRequest);
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
                Log.e("dflores", t.getStackTrace().toString());

            }
        });
    }

    private void llamarWsGrabarCategoria() {

        CategoriaRequest categoriaRequest = new CategoriaRequest();
        categoriaRequest.setTxt_name_categoria("Categoria DFLORES");
        categoriaRequest.setText_desc_categoria("Descripcion de David Flores plato vegano por favor!");

        MethodWs methodWs = HelperWs.getConfiguration().create(MethodWs.class);
        Call<ArrayList<Categoria>> response = methodWs.grabarCategoria(categoriaRequest);
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
                Log.e("dflores", t.getStackTrace().toString());

            }
        });

    }

    private void llamarWsObtenerUnaCategoria() {

        MethodWs methodWs = HelperWs.getConfiguration().create(MethodWs.class);
        Call<Categoria> response =methodWs.obtenerUnaCategoria("57654ebd2dae25dd0658389a");
        response.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {

                if(response.isSuccessful()){
                    Categoria categoria= response.body();

                    Log.i("dflores", categoria.get_id());
                    Log.i("dflores", categoria.getNombre());
                    Log.i("dflores", categoria.getDescripcion());
                    Log.i("dflores", "" + categoria.get__v());
                }

            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {

                Log.e("dflores", t.getMessage());
                Log.e("dflores", t.getStackTrace().toString());

            }
        });


    }

    private void llamarWsObtenerTodos() {

        pd = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pd.getProgressHelper().setBarColor(Color.parseColor("#102670"));
        pd.setContentText("Please, Waited.....!");
        pd.setCancelable(false);
        pd.show();

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

                pd.dismiss();

            }

            @Override
            public void onFailure(Call<ArrayList<Categoria>> call, Throwable t) {

                Log.e("dflores", t.getMessage());
                pd.dismiss();
            }
        });

    }
}
