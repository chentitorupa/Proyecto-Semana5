package com.charalito.petagramv2.fragment;

import com.charalito.petagramv2.adapter.Adaptador;
import com.charalito.petagramv2.adapter.PerfilAdapter;
import com.charalito.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public interface IPerfilPetFragmentView {

    public void generarGridLayout();

    //Adaptador de mascotas
    public PerfilAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(PerfilAdapter adaptador);
}
