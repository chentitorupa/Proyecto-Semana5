package com.charalito.petagramv2.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.charalito.petagramv2.R;
import com.charalito.petagramv2.adapter.Adaptador;
import com.charalito.petagramv2.fragment.IListOfPetsFragmentView;
import com.charalito.petagramv2.fragment.ListOfPetsFragment;
import com.charalito.petagramv2.pojo.Mascota;
import com.charalito.petagramv2.presentador.ListOfPetsFragmentPresenter;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity implements IListOfPetsFragmentView {

    private RecyclerView listaMascotasRaiting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle("5 Top Pets");

        listaMascotasRaiting = (RecyclerView) findViewById(R.id.rvMascotas2);

        new ListOfPetsFragmentPresenter(this,getBaseContext(),getClass().getName());
    }





    @Override
    public void generarLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotasRaiting.setLayoutManager(llm);

    }

    @Override
    public Adaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        return new Adaptador(mascotas, RatingActivity.this,new Fragment());
    }

    @Override
    public void inicializarAdaptadorRV(Adaptador adaptador) {
        listaMascotasRaiting.setAdapter(adaptador);
    }
}
