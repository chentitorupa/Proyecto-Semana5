package com.charalito.petagramv2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.charalito.petagramv2.R;
import com.charalito.petagramv2.adapter.Adaptador;
import com.charalito.petagramv2.pojo.Mascota;
import com.charalito.petagramv2.presentador.IListOfPetsFragmentPresenter;
import com.charalito.petagramv2.presentador.ListOfPetsFragmentPresenter;

import java.util.ArrayList;

public class ListOfPetsFragment extends Fragment implements IListOfPetsFragmentView {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IListOfPetsFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // La siguiente linea equivale al setContentView().
        View v = inflater.inflate(R.layout.fragment_list_of_pets,container,false);

        // Inicio RecyclerView
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        //Fin RecyclerView

        new ListOfPetsFragmentPresenter(this,getContext(),"");

        return v;
    }


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public Adaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        Adaptador adaptador = new Adaptador(mascotas, getActivity(),this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(Adaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
