package com.example.mapa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        GestorPreferencias gbdRest = new GestorPreferencias(PreferencesActivity.this);
        gbdRest.crear();
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch prefenciasMusica = findViewById(R.id.prefenciasMusica);
        boolean musicaActivada = gbdRest.getPreferenciasMusica();
        if(musicaActivada){
            prefenciasMusica.setChecked(true);
        }

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch prefenciasRuido = findViewById(R.id.prefenciasBotones);
        boolean ruidpActovadp = gbdRest.getPreferenciasRuido();
        if(ruidpActovadp){
            prefenciasRuido.setChecked(true);
        }

        prefenciasMusica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (prefenciasMusica.isChecked()){
                    gbdRest.changeEstatMusica();
                }else{
                    AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                    gbdRest.changeEstatMusicaUn();

                }
            }
        });

        prefenciasRuido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (prefenciasRuido.isChecked()){
                    gbdRest.changeEstatRuido();
                }else{
                    AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                    gbdRest.changeEstatRuidoUn();

                }
            }
        });


    }
}
