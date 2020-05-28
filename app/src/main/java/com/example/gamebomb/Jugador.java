package com.example.gamebomb;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String Jugador_Nombre;
    private String Jugador_Imagen;
    private int Jugador_Vida = 100;
    private int Jugador_NumCartas = 0;
    private ArrayList<String> Cartas = new ArrayList<>();

    //Constructor
    public Jugador(String Jugador_Nombre, String Jugador_Imagen, int Jugador_Vida,
                   int Jugador_NumCartas, ArrayList<String> Cartas)
    {
        this.Jugador_Nombre = Jugador_Nombre;
        this.Jugador_Vida = Jugador_Vida;
        this.Jugador_Imagen = Jugador_Imagen;
        this.Jugador_NumCartas = Jugador_NumCartas;
        this.Cartas = Cartas;
    }

    //Metodos
    public void setJugador_Nombre(String Dato)
    {
        Jugador_Nombre = Dato;
    }
    public void setJugador_Imagen(String Dato)
    {
        Jugador_Imagen = Dato;
    }
    public void setJugador_Vida(int Dato)
    {
        Jugador_Vida = Dato;
    }
    public void setJugador_Cartas(int Dato)
    {
        Jugador_NumCartas = Dato;
    }
    public ArrayList<String> getJugador_Lista()
    {
        return Cartas;
    }

    public int getJugador_Vida()
    {
        return Jugador_Vida;
    }

    public int getJugador_NumCartas()
    {
        return Jugador_NumCartas;
    }

    //Metodos de jugador
    public void Morir(String lugar)
    {
        Jugador_Vida = 0;
        Jugador_NumCartas = 0;
        Jugador_Imagen = lugar;
    }

    public void AgregarCarta(String carta)
    {
        Cartas.add(carta);
        Jugador_NumCartas++;
    }

    public void QuitarCarta(String carta)
    {
        Cartas.remove(carta);
        Jugador_NumCartas--;
    }

    public List<Jugador> Perder(List<Jugador> Lista_Jugadores)
    {
        Lista_Jugadores.remove(Lista_Jugadores.contains(Jugador_Nombre));
        return Lista_Jugadores;
    }

    public void Perder_Vida(int Dato)
    {
        //Verificar si pierde
        if (Dato >= Jugador_Vida)
        {
            Jugador_Vida = 0;
        }
        else Jugador_Vida = Jugador_Vida - Dato;
    }

    public void Ganar_Vida(int Dato)
    {
        //Verificar si pierde
        if (Dato + Jugador_Vida >= 100)
        {
            Jugador_Vida = 100;
        }
        else Jugador_Vida = Jugador_Vida + Dato;
    }
}
