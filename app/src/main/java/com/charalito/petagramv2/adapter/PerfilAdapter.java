package com.charalito.petagramv2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.charalito.petagramv2.R;
import com.charalito.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilMascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public PerfilAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil,parent,false);
        return new PerfilMascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PerfilMascotaViewHolder perfilMascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        perfilMascotaViewHolder.imgFotoPerfilMascota.setImageResource(mascota.getFoto());
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilMascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFotoPerfilMascota;
        private TextView tvNumeroLikesPerfil;

        public PerfilMascotaViewHolder(View itemView) {
            super(itemView);

            imgFotoPerfilMascota = (ImageView) itemView.findViewById(R.id.imgFotoPerfilMascota);
            tvNumeroLikesPerfil = (TextView) itemView.findViewById(R.id.tvNumeroLikesPerfil);
        }
    }
}
