package com.example.mapa;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botonMaps = findViewById(R.id.butonMaps);
        Button botonConciertos = findViewById(R.id.butonConciertos);

        ImageView prefences = findViewById(R.id.prefences);
        prefences.setClickable(true);
        prefences.bringToFront();

        GestorPreferencias gbdRest = new GestorPreferencias(MainActivity.this);
        gbdRest.crear();


        SoundPool soundPool1 = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        int idEfecteBoto1 = soundPool1.load(this, R.raw.efecte_boto, 0);

        prefences.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, PreferencesActivity.class);
                        startActivity(i);

                    }
                });


        botonMaps.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view) {
                        boolean ruidoActivada = gbdRest.getPreferenciasRuido();

                        if(ruidoActivada){
                            soundPool1.play(idEfecteBoto1, 1, 1, 1, 0, 1);
                        }
                        Intent i = new Intent(MainActivity.this, UbicacionesActivity.class);
                        startActivity(i);
                    }
                });

        botonConciertos.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view) {
                        boolean ruidoActivada = gbdRest.getPreferenciasRuido();

                        if(ruidoActivada){
                            soundPool1.play(idEfecteBoto1, 1, 1, 1, 0, 1);
                        }
                        Intent i = new Intent(MainActivity.this, ListarConciertosActivity.class);
                        startActivity(i);
                    }
                });


    }
}
