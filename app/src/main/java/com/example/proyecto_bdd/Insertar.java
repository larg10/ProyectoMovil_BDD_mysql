package com.example.proyecto_bdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Insertar extends AppCompatActivity {

    EditText nombre, apellido, edad, genero, ID;
    RequestQueue requestQueue;
    boolean ocupado = false;

    //private static final String URL1 = "http://192.168.0.6/intento_movil/save.php";
    private static final String URL1 = "https://bdmovil.000webhostapp.com/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        nombre = (EditText) findViewById(R.id.txtNombreCreate);
        apellido = (EditText) findViewById(R.id.txtApellidoCreate);
        edad = (EditText) findViewById(R.id.txtEdadCreate);
        genero = (EditText) findViewById(R.id.txtGeneroCreate);
        ID = (EditText) findViewById(R.id.txtIDCreate);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void Create(View view){
        ocupado = false;
        String nom = nombre.getText().toString().trim();
        String ap = apellido.getText().toString().trim();
        int ed = Integer.parseInt(edad.getText().toString().trim());
        String gen = genero.getText().toString().trim();
        String id = ID.getText().toString().trim();

        buscarInsertar();

        crear(nom,ap,ed,gen,id);

        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        genero.setText("");
        ID.setText("");
    }

    public void buscarInsertar() {

        String id = ID.getText().toString().trim();

        //String URL2 = "http://192.168.0.6/intento_movil/fetch.php?ID=" + id;
        String URL2 = "https://bdmovil.000webhostapp.com/fetch.php?ID=" + id;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Insertar.this, "Este ID est?? ocupado por otra persona, elija uno nuevo.",Toast.LENGTH_SHORT).show();
                        ocupado = true;
                        return;
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void crear( final String nombre, final String apellido, final int edad, final String genero, final String ID) {

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    URL1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(!ocupado) {
                                Toast.makeText(Insertar.this, "Insertados con ??xito", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(ocupado) {
                                Toast.makeText(Insertar.this, "No se pudieron insertar los datos.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Nombre", nombre);
                    params.put("Apellido", apellido);
                    params.put("Edad", String.valueOf(edad));
                    params.put("G??nero", genero);
                    params.put("ID", ID);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
