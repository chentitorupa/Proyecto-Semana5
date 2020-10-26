package com.charalito.petagramv2.db;

import android.content.ContentValues;
import android.content.Context;

import com.charalito.petagramv2.R;
import com.charalito.petagramv2.pojo.Mascota;

import java.util.ArrayList;
import java.util.Collections;

public class ConstructorMascotas {

    private Context context;
    private DataBase dataBase;

    public ConstructorMascotas(Context context){

        dataBase = new DataBase(context);

    }

    public ArrayList<Mascota> getPets(){
        return dataBase.getPets();
    }

    public int getLikes(Mascota mascota){
        return dataBase.getLikes(mascota);
    }
    public void insertLike(Mascota mascota){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_LIKES_AMOUNT,mascota.getLikes());
        dataBase.insertLike(contentValues,mascota);
    }
    public ArrayList<Mascota> getTopPets(){
        ArrayList<Mascota> pets = new ArrayList<>(getPets());
        ArrayList<Mascota> topPets = new ArrayList<>(pets);
        Collections.sort(topPets,Collections.<Mascota>reverseOrder());
        if (pets.size() > 5)
            topPets.subList(5, pets.size()).clear();
        return topPets;
    }

}
