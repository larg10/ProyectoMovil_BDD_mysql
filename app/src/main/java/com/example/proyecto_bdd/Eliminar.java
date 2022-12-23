package com.example.proyecto_bdd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Eliminar extends AppCompatActivity {

    EditText etID;
    TextView tvNombre, tvApellido, tvEdad, tvGenero;
    RequestQueue requestQueue;
    boolean busco = false;

    String nombre, apellido, edad, genero, ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        requestQueue = Volley.newRequestQueue(this);

        etID = (EditText) findViewById(R.id.txtIDDelete);

        tvNombre = (TextView) findViewById(R.id.txtNombre);
        tvApellido = (TextView) findViewById(R.id.txtApellido);
        tvEdad = (TextView) findViewById(R.id.txtEdad);
        tvGenero = (TextView) findViewById(R.id.txtGenero);

        busco = false;
    }

    public void buscarDelete(View view) {

        String id;
        id = etID.getText().toString();
        ID = id;

        String URL2 = "http://192.168.0.6/intento_movil/fetch.php?ID=" + id;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            nombre = response.getString("Nombre");
                            apellido = response.getString("Apellido");
                            edad = response.getString("Edad");
                            genero = response.getString("Género");
                            //ID = response.getString("ID");

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
                Toast.makeText(Eliminar.this, "No se encontraron datos que coincidan",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @SuppressLint("NotConstructor")
    public void Eliminar(View view){
        if(busco == true) {
            String URLDelete = "http://192.168.0.6/intento_movil/delete.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDelete,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            tvNombre.setText("");
                            tvApellido.setText("");
                            tvEdad.setText("");
                            tvGenero.setText("");

                            busco = false;
                            Toast.makeText(Eliminar.this, "Eliminando...", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Eliminar.this, "Por favor buscar algún elemento válido para eliminar.", Toast.LENGTH_SHORT).show();
                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("ID", ID);
                    return params;
                }
            };

            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(Eliminar.this, "Por favor buscar algún elemento válido para eliminar.", Toast.LENGTH_SHORT).show();
        }
    }
}