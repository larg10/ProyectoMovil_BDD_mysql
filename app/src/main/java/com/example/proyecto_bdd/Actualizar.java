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

import java.util.HashMap;
import java.util.Map;

public class Actualizar extends AppCompatActivity {

    EditText etID, tvNombre, tvApellido, tvEdad, tvGenero;
    RequestQueue requestQueue;
    boolean busco = false;

    String nombre, apellido, edad, genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        requestQueue = Volley.newRequestQueue(this);

        etID = (EditText) findViewById(R.id.txtIDUpdate);

        tvNombre = (EditText) findViewById(R.id.txtNombreUpdateAc);
        tvApellido = (EditText) findViewById(R.id.txtApellidoUpdateAc);
        tvEdad = (EditText) findViewById(R.id.txtEdadUpdateAc);
        tvGenero = (EditText) findViewById(R.id.txtGeneroUpdateAc);

        busco = false;
    }

    public void buscarUpdate(View view) {

        String id;
        id = etID.getText().toString();

        //String URL2 = "http://192.168.0.6/intento_movil/fetch.php?ID=" + id;
        String URL2 = "https://bdmovil.000webhostapp.com/fetch.php?ID=" + id;


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
            final String Nombre = tvNombre.getText().toString();
            final String Apellido = tvApellido.getText().toString();
            final String Edad = tvEdad.getText().toString();
            final String Genero = tvGenero.getText().toString();
            final String ID = etID.getText().toString();

            //String URLEdit = "http://192.168.0.6/intento_movil/edit.php";
            String URLEdit = "https://bdmovil.000webhostapp.com/edit.php";

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
                    params.put("Nombre", Nombre);
                    params.put("Apellido", Apellido);
                    params.put("Edad", Edad);
                    params.put("Género",Genero);
                    params.put("ID",ID);
                    return params;
                }
            };

            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(Actualizar.this, "Por favor buscar algún elemento válido para editar.", Toast.LENGTH_SHORT).show();
        }
    }
}