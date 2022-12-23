package com.example.proyecto_bdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Leer extends AppCompatActivity {

    EditText getID;
    TextView txtNombre, txtApellido, txtEdad, txtGenero;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer);

        requestQueue = Volley.newRequestQueue(this);

        getID = (EditText) findViewById(R.id.txtIDRead);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtApellido = (TextView) findViewById(R.id.txtApellido);
        txtEdad = (TextView) findViewById(R.id.txtEdad);
        txtGenero = (TextView) findViewById(R.id.txtGenero);

    }

    public void buscar(View view) {

        String id;
        id = getID.getText().toString();

        //String URL2 = "http://192.168.0.6/intento_movil/fetch.php?ID=" + id;
        String URL2 = "https://bdmovil.000webhostapp.com/fetch.php?ID=" + id;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL2, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String nombre, apellido, edad, genero, id;

                    try {
                        nombre = response.getString("Nombre");
                        apellido = response.getString("Apellido");
                        edad = response.getString("Edad");
                        genero = response.getString("Género");
                        id = response.getString("ID");

                        txtNombre.setText(nombre);
                        txtApellido.setText(apellido);
                        txtEdad.setText(edad);
                        txtGenero.setText(genero);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Toast.makeText(Leer.this, "No se encontraron datos que coincidan",Toast.LENGTH_SHORT).show();
                }
            });

        requestQueue.add(jsonObjectRequest);
    }

}