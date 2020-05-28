package com.example.gamebomb;

import java.util.ArrayList;

public class CartaClase {
    private String Carta_Nombre;
    private String Carta_Turno;

    public CartaClase(String Carta_Nombre, String Carta_Turno){
        this.Carta_Nombre = Carta_Nombre;
        this.Carta_Turno = Carta_Turno;
    }

    public String getCarta_Nombre()
    {
        return Carta_Nombre;
    }

    public String getCarta_Turno()
    {
        return Carta_Turno;
    }

}
