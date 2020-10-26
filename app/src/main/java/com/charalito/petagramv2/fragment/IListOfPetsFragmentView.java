package com.charalito.petagramv2.fragment;

import android.widget.Adapter;

import com.charalito.petagramv2.adapter.Adaptador;
import com.charalito.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public interface IListOfPetsFragmentView {

    public void generarLinearLayoutVertical();

    //Adaptador de mascotas
    public Adaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(Adaptador adaptador);
}
