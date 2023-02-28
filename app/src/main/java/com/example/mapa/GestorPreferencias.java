package com.example.mapa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class GestorPreferencias extends SQLiteOpenHelper {



    public GestorPreferencias(Context context) {
        super(context, "preferencias", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE preferencias ("
                + "id_preferencia INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "musica INT ,"
                + "ruido INT)");


    }

    public void crear(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM preferencias" ,null);

        if(!cursor.moveToFirst()){
            ContentValues cv = new ContentValues();
            cv.put("id_preferencia", 0);
            cv.put("musica", 0);
            cv.put("ruido", 0);

            db.insertOrThrow("preferencias", null, cv);
        }

    }

    public void changeEstatRuido(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE preferencias SET ruido = 1 WHERE id_preferencia = 0");
    }

    public void changeEstatRuidoUn(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE preferencias SET ruido = 0 WHERE id_preferencia = 0");
    }

    public void changeEstatMusica(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE preferencias SET musica = 1 WHERE id_preferencia = 0");
    }

    public void changeEstatMusicaUn(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE preferencias SET musica = 0 WHERE id_preferencia = 0");
    }

    @SuppressLint("Range")
    public boolean getPreferenciasMusica(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM preferencias WHERE id_preferencia=0" ,null);
        cursor.moveToFirst();
        if(Objects.equals(cursor.getString(cursor.getColumnIndex("musica")), "0")){
            return false;
        }
        return true;
    }

    @SuppressLint("Range")
    public boolean getPreferenciasRuido(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM preferencias WHERE id_preferencia=0" ,null);
        cursor.moveToFirst();
        if(Objects.equals(cursor.getString(cursor.getColumnIndex("ruido")), "0")){
            return false;
        }
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
