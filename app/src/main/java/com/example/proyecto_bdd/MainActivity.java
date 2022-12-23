package com.example.proyecto_bdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Insertar(View view){
        Intent insertar = new Intent(MainActivity.this,Insertar.class);
        startActivity(insertar);
    }

    public void Leer(View view){
        Intent leer = new Intent(MainActivity.this,Leer.class);
        startActivity(leer);
    }

    public void Actualizar(View view){
        Intent actualizar = new Intent(MainActivity.this,Actualizar.class);
        startActivity(actualizar);
    }

    public void Eliminar(View view){
        Intent eliminar = new Intent(MainActivity.this,Eliminar.class);
        startActivity(eliminar);
    }
}