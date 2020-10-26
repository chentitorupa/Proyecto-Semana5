package com.charalito.petagramv2.pojo;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Mascota implements Comparable<Mascota>, Parcelable {

    private int id;

    private String nombre;
    // Variable para contabilizar los likes
    private int likes;
    private int huesoYellow;

    private String cantidadRaiting;
    private int foto;


    public Mascota(int foto, String nombre, String cantidadRaiting, int likes, int
            huesoYellow) {
        this.foto = foto;
        this.nombre = nombre;
        this.cantidadRaiting = cantidadRaiting;
        this.likes = likes;
        this.huesoYellow = huesoYellow;
    }



    public Mascota(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getHuesoYellow() {
        return huesoYellow;
    }

    public void setHuesoYellow(int huesoYellow) {
        this.huesoYellow = huesoYellow;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public int compareTo(Mascota mascota) {
        return Integer.compare(likes, mascota.likes);
    }

    protected Mascota(Parcel in) {
        foto = in.readInt();
        nombre = in.readString();
        likes = in.readInt();
    }


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(foto);
        dest.writeString(nombre);
        dest.writeInt(likes);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Mascota> CREATOR = new Parcelable.Creator<Mascota>() {
        @Override
        public Mascota createFromParcel(Parcel in) {
            return new Mascota(in);
        }

        @Override
        public Mascota[] newArray(int size) {
            return new Mascota[size];
        }
    };


    public String getCantidadRaiting() {
        return cantidadRaiting;
    }

    public void setCantidadRaiting(String cantidadRaiting) {
        this.cantidadRaiting = cantidadRaiting;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
