package com.example.gamebomb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PantallaJuego extends AppCompatActivity {

    //Variables globales
    int damage = 25;
    int imagen;
    int jugador = 0;
    int jugadores_vivos = 3;
    boolean jugo = false;
    boolean robo = false;
    CartaClase cartaClase = new CartaClase("","");
    String turno = "normal";
    ArrayList<String> deck = new ArrayList<>();
    ArrayList<Jugador> jugadores = new ArrayList<>();
    ArrayList<CartaClase> cartaClases = new ArrayList<>();
    Handler handler = new Handler();
    Handler handler2 = new Handler();

    //Componentes
    ImageView img_deck;
    ImageView img_cementerio;
    ImageView img_turno;
    RecyclerView recyclerView;
    AdapterCartas adapterCartas;
    TextView txt_damage;
    TextView txt_jugador_vida1;
    TextView txt_jugador_vida2;
    TextView txt_jugador_vida3;
    TextView txt_jugador_vida4;
    TextView txt_jugador_cartas2;
    TextView txt_jugador_cartas3;
    TextView txt_jugador_cartas4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego);

        img_deck = (ImageView)findViewById(R.id.img_deck);
        img_cementerio = (ImageView)findViewById(R.id.img_cementerio);
        img_turno = (ImageView)findViewById(R.id.img_tipo_turno);

        txt_damage = (TextView)findViewById(R.id.txt_damage);
        txt_jugador_vida1 = (TextView)findViewById(R.id.txt_jugador_vida1);
        txt_jugador_vida2 = (TextView)findViewById(R.id.txt_jugador_vida2);
        txt_jugador_vida3 = (TextView)findViewById(R.id.txt_jugador_vida3);
        txt_jugador_vida2 = (TextView)findViewById(R.id.txt_jugador_vida4);
        txt_jugador_cartas2 = (TextView)findViewById(R.id.txt_jugador_cartas2);
        txt_jugador_cartas3 = (TextView)findViewById(R.id.txt_jugador_cartas3);
        txt_jugador_cartas4 = (TextView)findViewById(R.id.txt_jugador_cartas4);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_cartas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        img_deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Robar();
            }
        });

        img_cementerio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculoDeDamage(jugador);
                FinTurno();
                JugarPC();
            }
        });
        GenerarDeck();
        GenerarCartas();
        Barajar();
        AgregarJugadores();
        GenerarDeck2();
        Barajar();
        MostrarTopeDeck();
        ActualizarCartas();
    }

    void GenerarCartas()
    {
        cartaClases.add(new CartaClase("BombaD","ambos"));
        cartaClases.add(new CartaClase("Cambio","normal"));
        cartaClases.add(new CartaClase("Desarme","rapido"));
        cartaClases.add(new CartaClase("Dia","rapido"));
        cartaClases.add(new CartaClase("Duplicador","rapido"));
        cartaClases.add(new CartaClase("Llamada","normal"));
        cartaClases.add(new CartaClase("Regresar","rapido"));
        cartaClases.add(new CartaClase("BombaI","rapido"));
    }

    void GenerarDeck2() {
        int c = 10;
        while (c > 0)
        { deck.add("BombaI");
            c--; }
    }

    void GenerarDeck()
    {
        int c = 8;

        while (c > 0)
        { deck.add("BombaD");
            c--; }

        c = 4;
        while (c > 0)
        { deck.add("Cambio");
            c--; }

        c = 4;
        while (c > 0)
        { deck.add("Desarme");
            c--; }

        c = 2;
        while (c > 0)
        { deck.add("Dia");
            c--; }

        c = 4;
        while (c > 0)
        { deck.add("Duplicador");
            c--; }

        c = 4;
        while (c > 0)
        { deck.add("Llamada");
            c--; }

        /*
        c = 4;
        while (c > 0)
        { deck.add("Regresar");
            c--; }

         */
    }

    void Barajar()
    {
        ArrayList<String> deck2 = new ArrayList<>();
        Random numero = new Random();
        int c;
        while (deck.size() > 0) {
            c = numero.nextInt(deck.size()-0);
            deck2.add(deck.get(c));
            deck.remove(c);
        }
        deck = deck2;
    }

    void AgregarJugadores()
    {
        int c = 0;
        while (c < 4)
        {
            jugadores.add(new Jugador(Integer.toString(c),"",100, 0, new ArrayList<String>()));
            jugadores.get(c).AgregarCarta("Desarme");
            jugadores.get(c).AgregarCarta(deck.get(0));
            jugadores.get(c).AgregarCarta(deck.get(1));
            deck.remove(0);
            deck.remove(0);
            c++;
        }
    }

    void MostrarTopeDeck()
    {
        if (deck.get(0).equals("BombaI"))
            imagen = R.drawable.bomba_ih;
        else
            imagen = R.drawable.boca_h;

        img_deck.setImageResource(imagen);
    }

    void ActualizarCementerio(String carta_seleccionada)
    {
        int image = 0;
        switch (carta_seleccionada)
        {
            case "BombaD":
                image = R.drawable.bomba_d;
                break;

            case "Cambio":
                image = R.drawable.cambio;
                break;

            case "Desarme":
                image = R.drawable.desarme;
                break;

            case "Dia":
                image = R.drawable.dia;
                break;

            case "Duplicador":
                image = R.drawable.duplicador;
                break;

            case "Llamada":
                image = R.drawable.llamada;
                break;

            case "Regresar":
                image = R.drawable.regresar;
                break;

            case "BombaI":
                image = R.drawable.bomba_i;
                break;
        }
        img_cementerio.setImageResource(image);
    }

    void  ActualizarCartas()
    {
        adapterCartas = new AdapterCartas(jugadores.get(0).getJugador_Lista());

        adapterCartas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carta_seleccionada = jugadores.get(0).getJugador_Lista().get(recyclerView.getChildAdapterPosition(v));
                cartaClase = AsignarCarta(carta_seleccionada);

                if (jugo)
                {
                    Toast.makeText(getApplicationContext(),"Ya jugaste una carta",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (cartaClase.getCarta_Turno().equals(turno) || cartaClase.getCarta_Turno().equals("ambos"))
                    {
                        jugadores.get(0).QuitarCarta(carta_seleccionada);
                        ActualizarCementerio(carta_seleccionada);
                        jugo = true;
                        ActualizarCartas();
                        EfectoCarta(carta_seleccionada);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"El turno no es compatible con la carta",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        recyclerView.setAdapter(adapterCartas);
    }

    CartaClase AsignarCarta(String carta_seleccionada)
    {
        switch (carta_seleccionada)
        {
            case "BombaD":
                cartaClase = cartaClases.get(0);
                break;

            case "Cambio":
                cartaClase = cartaClases.get(1);
                break;

            case "Desarme":
                cartaClase = cartaClases.get(2);
                break;

            case "Dia":
                cartaClase = cartaClases.get(3);
                break;

            case "Duplicador":
                cartaClase = cartaClases.get(4);
                break;

            case "Llamada":
                cartaClase = cartaClases.get(5);
                break;

            case "Regresar":
                cartaClase = cartaClases.get(6);
                break;

            case "BombaI":
                cartaClase = cartaClases.get(7);
                break;
        }
        return cartaClase;
    }

    void CambiarVida(int jugador, int vida)
    {
        switch (jugador)
        {
            case 0:
                txt_jugador_vida1.setText(Integer.toString(vida));
                break;

            case 1:
                txt_jugador_vida2.setText(Integer.toString(vida));
                break;

            case 2:
                txt_jugador_vida3.setText(Integer.toString(vida));
                break;

            case 3:
                txt_jugador_vida4.setText(Integer.toString(vida));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    void CambiarCartas(int jugador, int cartas)
    {
        switch (jugador)
        {
            case 1:
                txt_jugador_cartas2.setText(Integer.toString(cartas));
                break;

            case 2:
                txt_jugador_cartas3.setText(Integer.toString(cartas));
                break;

            case 3:
                txt_jugador_cartas4.setText(Integer.toString(cartas));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    void EfectoCarta(String carta_seleccionada)
    {
        switch (carta_seleccionada)
        {
            case "BombaD":
                damage = damage + 25;
                txt_damage.setText(Integer.toString(damage));
                turno = "rapido";
                img_turno.setImageResource(R.drawable.rapido);
                FinTurno();
                if (jugador == 0)
                {
                    JugarPC();
                }
                break;

            case "Cambio":
                damage = 0;
                txt_damage.setText(Integer.toString(damage));
                Barajar();
                MostrarTopeDeck();
                break;

            case "Desarme":
                if (damage <=100)
                    damage = 0;
                else
                    damage = damage -100;
                txt_damage.setText(Integer.toString(damage));
                turno = "normal";
                img_turno.setImageResource(R.drawable.normal1);
                CalculoDeDamage(jugador);
                FinTurno();
                if (jugador == 0)
                {
                    JugarPC();
                }
                break;

            case "Dia":
                damage = 0;
                txt_damage.setText(Integer.toString(damage));
                jugadores.get(jugador).setJugador_Vida(100);
                CambiarVida(jugador,100);
                turno = "normal";
                img_turno.setImageResource(R.drawable.normal1);
                FinTurno();
                if (jugador == 0)
                {
                    JugarPC();
                }
                break;

            case "Duplicador":
                damage = damage * 2;
                txt_damage.setText(Integer.toString(damage));
                FinTurno();
                if (jugador == 0)
                {
                    JugarPC();
                }
                break;

            case "Llamada":
                damage = 0;
                txt_damage.setText(Integer.toString(damage));
                jugadores.get(jugador).Ganar_Vida(25);
                CambiarVida(jugador,jugadores.get(jugador).getJugador_Vida());
                break;

            case "Regresar":
                break;

            case "BombaI":
                damage = damage + 50;
                txt_damage.setText(Integer.toString(damage));
                turno = "rapido";
                img_turno.setImageResource(R.drawable.rapido);
                break;
        }
    }

    void Robar()
    {
        if (turno.equals("normal") && !robo)
        {
            if (deck.get(0).equals("BombaI"))
            {
                EfectoCarta("BombaI");
                ActualizarCementerio("BombaI");
            }
            else
            {
                jugadores.get(jugador).AgregarCarta(deck.get(0));
                CambiarCartas(jugador,jugadores.get(jugador).getJugador_NumCartas());
            }
            deck.remove(0);
            MostrarTopeDeck();
            ActualizarCartas();
            robo = true;
        }
        else
            Toast.makeText(getApplicationContext(),"No puedes robar mas cartas este turno",Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("SetTextI18n")
    void CalculoDeDamage(int jugador)
    {
        jugadores.get(jugador).Perder_Vida(damage);
        CambiarVida(jugador,jugadores.get(jugador).getJugador_Vida());

        if (jugadores.get(jugador).getJugador_Vida() == 0)
        {
            if (jugador == 0)
            {
                Toast.makeText(getApplicationContext(),"Haz perdido todos tus puntos",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            jugadores_vivos--;
            if (jugadores_vivos == 1)
            {
                Toast.makeText(getApplicationContext(),"Haz ganado",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }
        damage = 0;
        txt_damage.setText(Integer.toString(damage));
        turno = "normal";
        img_turno.setImageResource(R.drawable.normal1);
    }

    @SuppressLint("SetTextI18n")
    void FinTurno()
    {
        damage = damage + 25;
        txt_damage.setText(Integer.toString(damage));
        jugo = false;
        robo = false;
    }

    void JugarPC()
    {
        Toast.makeText(getApplicationContext(),"Fin de tu turno",Toast.LENGTH_LONG).show();

        TurnoPC();
        handler2.postDelayed(runnableTurnoPC2,10000);
        //handler2.postDelayed(runnableTurnoPC3,20000);
        handler2.postDelayed(runnableRestaurar,20000);
    }

    void TurnoPC()
    {
        jugador = 1;
        if (jugadores.get(jugador).getJugador_Vida() != 0)
        {

            if (turno.equals("normal"))
            {
                handler.postDelayed(runnableRobar,3000);
            }
            handler.postDelayed(runnableJugar,6000);
            handler.postDelayed(runnableCalcularDamage,9000);
        }
    }

    void TurnoPC2()
    {
        jugador = 2;
        if (jugadores.get(jugador).getJugador_Vida() != 0)
        {

            if (turno.equals("normal"))
            {
                handler.postDelayed(runnableRobar,3000);
            }
            handler.postDelayed(runnableJugar,6000);
            handler.postDelayed(runnableCalcularDamage,9000);
        }
    }

    void TurnoPC3()
    {
        jugador = 3;
        if (jugadores.get(jugador).getJugador_Vida() != 0)
        {
            if (turno.equals("normal"))
            {
                handler.postDelayed(runnableRobar,3000);
            }
            handler.postDelayed(runnableJugar,6000);
            handler.postDelayed(runnableCalcularDamage,9000);
        }
    }

    private Runnable runnableRobar = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getApplicationContext(),"Adversario: Roba carta",Toast.LENGTH_SHORT).show();
            Robar();
        }
    };

    private Runnable runnableJugar = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getApplicationContext(),"Adversario: Juega Carta",Toast.LENGTH_SHORT).show();
            int c = 0;
            String carta_seleccionada = "";

            while (c <= jugadores.get(jugador).getJugador_Lista().size())
            {
                carta_seleccionada = jugadores.get(jugador).getJugador_Lista().get(c);
                cartaClase = AsignarCarta(carta_seleccionada);

                if (cartaClase.getCarta_Turno().equals(turno) || cartaClase.getCarta_Turno().equals("ambos"))
                {
                    jugadores.get(jugador).QuitarCarta(carta_seleccionada);
                    ActualizarCementerio(carta_seleccionada);
                    CambiarCartas(jugador,jugadores.get(jugador).getJugador_NumCartas());
                    EfectoCarta(carta_seleccionada);
                    jugo = true;
                    break;
                }
                c++;
            }
        }
    };

    private Runnable runnableCalcularDamage = new Runnable() {
        @Override
        public void run() {
            if (turno.equals("normal"))
            {
                if (!jugo)
                {
                    Toast.makeText(getApplicationContext(),"Adversario: Recibe Daño",Toast.LENGTH_SHORT).show();
                    CalculoDeDamage(jugador);
                    FinTurno();
                }
                else
                {
                    FinTurno();
                }
            }
            else
            {
                if (!jugo)
                {
                    Toast.makeText(getApplicationContext(),"Adversario: Recibe Daño",Toast.LENGTH_SHORT).show();
                    CalculoDeDamage(jugador);
                    FinTurno();
                }
            }
        }
    };

    private Runnable runnableTurnoPC2 = new Runnable() {
        @Override
        public void run() {
            TurnoPC2();
        }
    };

    private Runnable runnableTurnoPC3 = new Runnable() {
        @Override
        public void run() {
            TurnoPC3();
        }
    };

    private Runnable runnableRestaurar = new Runnable() {
        @Override
        public void run() {
            jugo = false;
            robo = false;
            jugador = 0;
        }
    };
}
