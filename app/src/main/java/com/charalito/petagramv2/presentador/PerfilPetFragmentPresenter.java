package com.charalito.petagramv2.presentador;

import android.content.Context;

import com.charalito.petagramv2.db.ConstructorMascotas;
import com.charalito.petagramv2.fragment.IPerfilPetFragmentView;
import com.charalito.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class PerfilPetFragmentPresenter implements IPerfilPetFragmentPresenter {

    private IPerfilPetFragmentView iPerfilPetFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;


    public PerfilPetFragmentPresenter(IPerfilPetFragmentView iPerfilPetFragmentView, Context context){
        this.iPerfilPetFragmentView = iPerfilPetFragmentView;
        this.context = context;
        obtenerMascotasDB();
    }

    @Override
    public void obtenerMascotasDB() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.getPets();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iPerfilPetFragmentView.inicializarAdaptadorRV(iPerfilPetFragmentView.crearAdaptador(mascotas));
        iPerfilPetFragmentView.generarGridLayout();
    }
}
