package com.charalito.petagramv2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.charalito.petagramv2.R;
import com.charalito.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private Context context;

    public DataBase(Context context) {
        super(context,ConstantesDB.DATABASE_NAME,null,ConstantesDB.DATABASE_VERSION);
        this.context  = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreatePetTable = "CREATE TABLE " + ConstantesDB.TABLE_PETS + "("+
                ConstantesDB.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLE_PETS_NAME + " TEXT, " +
                ConstantesDB.TABLE_PETS_IMG + " INTEGER" +
                ")";
        String queryCreatePetLikesTable = "CREATE TABLE " + ConstantesDB.TABLE_LIKES + "(" +
                ConstantesDB.TABLE_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLE_LIKES_ID_PET + " INTEGER, " +
                ConstantesDB.TABLE_LIKES_AMOUNT + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesDB.TABLE_LIKES_ID_PET + ") " +
                "REFERENCES " + ConstantesDB.TABLE_PETS + "(" + ConstantesDB.TABLE_PETS_ID + ")" +
                ")";

        sqLiteDatabase.execSQL(queryCreatePetTable);
        sqLiteDatabase.execSQL(queryCreatePetLikesTable);

        insertPets(sqLiteDatabase);
        setDefaultLikes(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLE_PETS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLE_LIKES);

        onCreate(sqLiteDatabase);
    }

    public void insertPets(SQLiteDatabase sqLiteDatabase){
        insertPet(sqLiteDatabase,new ContentValues(),ConstantesDB.TABLE_PETS_NAME,"Tobby",ConstantesDB.TABLE_PETS_IMG, R.drawable.perro1);
        insertPet(sqLiteDatabase,new ContentValues(),ConstantesDB.TABLE_PETS_NAME,"Nicolas",ConstantesDB.TABLE_PETS_IMG, R.drawable.perro2);
        insertPet(sqLiteDatabase,new ContentValues(),ConstantesDB.TABLE_PETS_NAME,"Terry",ConstantesDB.TABLE_PETS_IMG, R.drawable.perro3);
        insertPet(sqLiteDatabase,new ContentValues(),ConstantesDB.TABLE_PETS_NAME,"Sparky",ConstantesDB.TABLE_PETS_IMG, R.drawable.perro4);
        insertPet(sqLiteDatabase,new ContentValues(),ConstantesDB.TABLE_PETS_NAME,"Mushu",ConstantesDB.TABLE_PETS_IMG, R.drawable.gato1);
        insertPet(sqLiteDatabase,new ContentValues(),ConstantesDB.TABLE_PETS_NAME,"Ernesto",ConstantesDB.TABLE_PETS_IMG, R.drawable.gato3);
        insertPet(sqLiteDatabase,new ContentValues(),ConstantesDB.TABLE_PETS_NAME,"Bastet",ConstantesDB.TABLE_PETS_IMG, R.drawable.gato2);
        insertPet(sqLiteDatabase,new ContentValues(),ConstantesDB.TABLE_PETS_NAME,"Viviano",ConstantesDB.TABLE_PETS_IMG, R.drawable.gato4);
    }
    public void insertPet(SQLiteDatabase sqLiteDatabase,ContentValues contentValues, String nameField, String nameValue, String imgField, int imgValue){
        contentValues.put(nameField,nameValue);
        contentValues.put(imgField, imgValue);
        sqLiteDatabase.insert(ConstantesDB.TABLE_PETS,null,contentValues);
    }
    public void setDefaultLikes(SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
        String query = "SELECT "+ConstantesDB.TABLE_PETS_ID+" FROM " + ConstantesDB.TABLE_PETS;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        while(cursor.moveToNext()){
            contentValues.put(ConstantesDB.TABLE_LIKES_ID_PET,cursor.getInt(0));
            contentValues.put(ConstantesDB.TABLE_LIKES_AMOUNT,0);
            sqLiteDatabase.insert(ConstantesDB.TABLE_LIKES,null,contentValues);
        }

        cursor.close();
    }
    public void insertLike(ContentValues contentValues, Mascota pet){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(ConstantesDB.TABLE_LIKES,contentValues,ConstantesDB.TABLE_LIKES_ID_PET+"="+pet.getId(),null);
        sqLiteDatabase.close();
    }
    public ArrayList<Mascota> getPets(){
        ArrayList<Mascota> pets = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + ConstantesDB.TABLE_PETS;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        while(cursor.moveToNext()){
            Mascota myPet = new Mascota();
            myPet.setId(cursor.getInt(0));
            myPet.setNombre(cursor.getString(1));
            myPet.setFoto(cursor.getInt(2));

            pets.add(myPet);
        }

        query = "SELECT " + ConstantesDB.TABLE_LIKES_AMOUNT + "," +ConstantesDB.TABLE_LIKES_ID_PET + " FROM " + ConstantesDB.TABLE_LIKES;
        cursor = sqLiteDatabase.rawQuery(query,null);

        while(cursor.moveToNext())
            for(int i = 0 ; i < pets.size();i++)
                if(pets.get(i).getId() == cursor.getInt(1))
                    pets.get(i).setLikes(cursor.getInt(0));

        cursor.close();
        sqLiteDatabase.close();
        return pets;
    }
    public int getLikes(Mascota pet){
        int likes = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "SELECT " + ConstantesDB.TABLE_LIKES_AMOUNT +
                " FROM " + ConstantesDB.TABLE_LIKES +
                " WHERE " + ConstantesDB.TABLE_LIKES_ID_PET + "="+pet.getId();

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToNext())
            likes = cursor.getInt(0);

        sqLiteDatabase.close();
        cursor.close();
        return likes;
    }
}
