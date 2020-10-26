package com.charalito.petagramv2.presentador;

import android.content.Context;

import com.charalito.petagramv2.activities.RatingActivity;
import com.charalito.petagramv2.adapter.Adaptador;
import com.charalito.petagramv2.db.ConstructorMascotas;
import com.charalito.petagramv2.fragment.IListOfPetsFragmentView;
import com.charalito.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class ListOfPetsFragmentPresenter implements IListOfPetsFragmentPresenter {

    private IListOfPetsFragmentView iListOfPetsFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Mascota> topPets;
    private String className;


    public ListOfPetsFragmentPresenter(IListOfPetsFragmentView iListOfPetsFragmentView, Context context,String className){
        this.iListOfPetsFragmentView = iListOfPetsFragmentView;
        constructorMascotas = new ConstructorMascotas(context);
        this.className = className;
        getPets();
    }

    @Override
    public void getPets() {
        mascotas = constructorMascotas.getPets();
        topPets = constructorMascotas.getTopPets();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        if(className.equals(RatingActivity.class.getName())){
            iListOfPetsFragmentView.inicializarAdaptadorRV(iListOfPetsFragmentView.crearAdaptador(topPets));

        }else{
            iListOfPetsFragmentView.inicializarAdaptadorRV(iListOfPetsFragmentView.crearAdaptador(mascotas));

        }
        iListOfPetsFragmentView.generarLinearLayoutVertical();
    }
}
