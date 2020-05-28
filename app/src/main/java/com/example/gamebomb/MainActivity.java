package com.example.gamebomb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Variables globales
    private ImageView un_jugador;

    private ImageView en_linea;
    private ImageView como_jugar;
    private ImageView salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        un_jugador = (ImageView)findViewById(R.id.un_jugador);
        en_linea = (ImageView)findViewById(R.id.en_linea);
        como_jugar = (ImageView)findViewById(R.id.como_jugar);
        salir = (ImageView)findViewById(R.id.salir);

        un_jugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Abrir_Pantalla();
            }
        });

        en_linea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Abrir_Sala();
            }
        });

        como_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Abrir_Tutorial();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });


    }


    public void Abrir_Pantalla()
    {
        Intent intent = new Intent(this,PantallaJuego.class);
        startActivity(intent);
    }

    public void Abrir_Sala()
    {
        Intent intent = new Intent(this,SalaDeJuego.class);
        startActivity(intent);
    }

    public void Abrir_Tutorial()
    {
        Intent intent = new Intent(this,Tutorial.class);
        startActivity(intent);
    }
}
