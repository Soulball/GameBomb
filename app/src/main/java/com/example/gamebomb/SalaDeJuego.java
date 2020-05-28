package com.example.gamebomb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SalaDeJuego extends AppCompatActivity {

    private Button but_unirse;
    private Button but_crear;
    private Button but_inicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_de_juego);

        but_unirse = (Button)findViewById(R.id.but_unirse);
        but_crear = (Button)findViewById(R.id.but_crear);
        but_inicar = (Button)findViewById(R.id.but_iniciar);

        but_unirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"No hay una sala con ese nombre",Toast.LENGTH_SHORT).show();
            }
        });

        but_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"No tienes permisos para crear una sala",Toast.LENGTH_SHORT).show();
            }
        });

        but_inicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"No eres dueño de salas",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
