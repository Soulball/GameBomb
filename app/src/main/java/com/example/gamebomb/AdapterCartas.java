package com.example.gamebomb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCartas extends RecyclerView.Adapter<AdapterCartas.ViewHolderCartas> implements View.OnClickListener {

    ArrayList<String> List_Cartas;
    private View.OnClickListener listener;

    public AdapterCartas(ArrayList<String> list_Cartas) {
        List_Cartas = list_Cartas;
    }

    @NonNull
    @Override
    public ViewHolderCartas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cartas,null,false);
        view.setOnClickListener(this);
        return new ViewHolderCartas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCartas holder, int position) {
        holder.asignar(List_Cartas.get(position));
    }

    @Override
    public int getItemCount() {
        return List_Cartas.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

        if (listener != null )
            listener.onClick(v);

    }

    public class ViewHolderCartas extends RecyclerView.ViewHolder {

        ImageView carta;

        public ViewHolderCartas(@NonNull View itemView) {
            super(itemView);
            carta = (ImageView)itemView.findViewById(R.id.img_carta);
        }

        public void asignar(String s) {
            int image = R.drawable.bomba_i;
            switch (s)
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
            }

            carta.setImageResource(image);
        }
    }
}
