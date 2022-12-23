package com.example.proyecto_bdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Actualizar extends AppCompatActivity {

    EditText etNombre, etApellido;
    TextView tvNombre, tvApellido, tvEdad, tvGenero;
    RequestQueue requestQueue;
    boolean busco = false;

    String nombre, apellido, edad, genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        requestQueue = Volley.newRequestQueue(this);

        etNombre = (EditText) findViewById(R.id.txtNombreUpdate);
        etApellido = (EditText) findViewById(R.id.txtApellidoUpdate);

        tvNombre = (EditText) findViewById(R.id.txtNombreUpdateAc);
        tvApellido = (EditText) findViewById(R.id.txtApellidoUpdateAc);
        tvEdad = (EditText) findViewById(R.id.txtEdadUpdateAc);
        tvGenero = (EditText) findViewById(R.id.txtGeneroUpdateAc);

        busco = false;
    }

    public void buscarUpdate(View view) {

        String nom,ap;
        nom = etNombre.getText().toString();
        ap = etApellido.getText().toString();

        String URL2 = "http://192.168.0.14/intento_movil/fetch.php?Nombre=" + nom + "&Apellido=" + ap;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            nombre = response.getString("Nombre");
                            apellido = response.getString("Apellido");
                            edad = response.getString("Edad");
                            genero = response.getString("Género");

                            tvNombre.setText(nombre);
                            tvApellido.setText(apellido);
                            tvEdad.setText(edad);
                            tvGenero.setText(genero);

                            busco = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(Actualizar.this, "No se encontraron datos que coincidan",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void Editar(View view){
        if(busco == true) {
            final String nom = tvNombre.getText().toString();
            final String ape = tvApellido.getText().toString();
            final String eda = tvEdad.getText().toString();
            final String gen = tvGenero.getText().toString();

            String URLEdit = "http://192.168.0.14/intento_movil/edit.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEdit,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            busco = false;
                            Toast.makeText(Actualizar.this, "Actualizando datos...", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Actualizar.this, "Por favor buscar algún elemento válido para editar.", Toast.LENGTH_SHORT).show();
                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Nombre", nom);
                    params.put("Apellido", ape);
                    params.put("Edad", eda);
                    params.put("Género",gen);
                    return params;
                }
            };

            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(Actualizar.this, "Por favor buscar algún elemento válido para editar.", Toast.LENGTH_SHORT).show();
        }
    }
}