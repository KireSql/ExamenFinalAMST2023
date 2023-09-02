package com.example.examenamst;

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

    public void irDatosSensados(View view){
        Intent intent = new Intent(this, DatosSensados.class);
        startActivity(intent);
    }

    public void irEstadoBateria(View view){
        Intent intent = new Intent(this, EstadoBateria.class);
        startActivity(intent);
    }
}