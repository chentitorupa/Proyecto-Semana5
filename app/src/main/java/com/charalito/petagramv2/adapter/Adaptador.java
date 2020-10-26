package com.charalito.petagramv2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.charalito.petagramv2.MainActivity;
import com.charalito.petagramv2.activities.RatingActivity;
import com.charalito.petagramv2.db.ConstructorMascotas;
import com.charalito.petagramv2.fragment.ListOfPetsFragment;
import com.charalito.petagramv2.fragment.PerfilPetFragment;
import com.charalito.petagramv2.pojo.Mascota;
import com.charalito.petagramv2.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ContactoViewHolder>{
    ArrayList<Mascota> contactos;
    Activity activity;
    Fragment fragment;


    //Metodo constructor que pasa la lista
    public Adaptador (ArrayList<Mascota> contactos, Activity activity, Fragment fragment){
        this.contactos = contactos;
        this.activity = activity;
        this.fragment = fragment;

    }


    @NonNull
    @Override
    //Inflar layout y lo pasara al ViewHolder para que obtenga cada elemento
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        //Le da vida al layout se busca asociar al recycler view
        return new ContactoViewHolder(v);
    }

    //asocia cada elemento de la vista con cada view
    @Override
    public void onBindViewHolder(@NonNull final ContactoViewHolder contactoViewHolder, int position) {//setear valores de la lista
        final Mascota contacto = contactos.get(position);
        final ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);

        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());

        if(activity.getClass().getName().equals(MainActivity.class.getName())){
            if(fragment.getClass().getName().equals(ListOfPetsFragment.class.getName())){
                contacto.setLikes(constructorMascotas.getLikes(contacto));
                // Elemento de likes
                contactoViewHolder.tvRaitingCV.setText(String.valueOf(contacto.getLikes()));
                activity.registerForContextMenu(contactoViewHolder.imgFoto);
                contactoViewHolder.tvNombreCV.setWidth(700);

                // Botón para darle like
                contactoViewHolder.imgRaitingBone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contacto.setLikes(contacto.getLikes()+1);
                        constructorMascotas.insertLike(contacto);
                        contactoViewHolder.tvRaitingCV.setText(String.valueOf(contacto.getLikes()));
                        Toast.makeText(activity,"Like for "+contacto.getNombre(),Toast.LENGTH_SHORT).show();
                    }
                });
            }else if (fragment.getClass().getName().equals(PerfilPetFragment.class.getName())){
                contactoViewHolder.tvRaitingCV.setText(String.valueOf(contacto.getLikes()));
                contactoViewHolder.imgRaitingBone.setVisibility(View.INVISIBLE);
            }
        }else if(activity.getClass().getName().equals(RatingActivity.class.getName())){
            contactoViewHolder.tvRaitingCV.setText(String.valueOf(contacto.getLikes()));
            contactoViewHolder.imgRaitingBone.setVisibility(View.INVISIBLE);
        }

        /*
        contactoViewHolder.imgRaitingBone.setImageResource(contacto.getLikes());
        contactoViewHolder.imgBoneYellow.setImageResource(contacto.getHuesoYellow());
           */





    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene la lista
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombreCV;
        // Esta variable tendrá los likes
        private TextView tvRaitingCV;
        private ImageButton imgRaitingBone;
        private ImageView imgBoneYellow;



        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView)itemView.findViewById(R.id.imgFoto);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvRaitingCV = (TextView) itemView.findViewById(R.id.tvRaitingCV);
            imgBoneYellow = (ImageView)itemView.findViewById(R.id.imgdogBoneYellow);
            imgRaitingBone = (ImageButton)itemView.findViewById(R.id.imgdogBoneWhite);
        }
    }
}