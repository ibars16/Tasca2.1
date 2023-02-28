package com.example.mapa;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ListarConciertosActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    public static final String[] NOMBRE = {"test"};
    public static final String[] IMAGEN = {"test"};
    public static final String[] FECHA = {"test"};
    MediaPlayer mp;
    private AudioManager mAudioManager;
    private int posMusic;
    int currentPositon = 0;
    private ImageView resume;

    private void setLink(TextView textView, String url) {
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='" + url + "'>https://www.axs.com/events/439929/melendi-tickets</a>";
        textView.setText(Html.fromHtml(text));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        setContentView(R.layout.activity_listar_conciertos);
        int[] raws = {R.raw.melend, R.raw.melendi2, R.raw.melendi3};

        GestorPreferencias gbdRest = new GestorPreferencias(ListarConciertosActivity.this);
        boolean musicaActivada = gbdRest.getPreferenciasMusica();
        SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        int idEfecteBoto = soundPool.load(this, R.raw.sylvia_communis, 0);


        //Reproduïm la música de fons

        ImageView back = findViewById(R.id.back);
        back.setClickable(true);
        back.bringToFront();
        ImageView next = findViewById(R.id.next);
        next.setClickable(true);
        next.bringToFront();
        ImageView restart = findViewById(R.id.restart);
        restart.setClickable(true);
        restart.bringToFront();
        resume = findViewById(R.id.resume);
        resume.setClickable(true);
        resume.bringToFront();


        TextView linkConcert1 = findViewById(R.id.linkTexas);
        TextView linkConcert2 = findViewById(R.id.linkHouston);
        TextView linkConcert3 = findViewById(R.id.linkOrlando);
        TextView linkConcert4 = findViewById(R.id.linkMiami);
        TextView linkConcert5 = findViewById(R.id.linkYork);


        this.setLink(linkConcert1, "https://www.axs.com/events/439929/melendi-tickets");
        this.setLink(linkConcert2, "https://www.ticketmaster.com/event/3A005CE1B77E4A48");
        this.setLink(linkConcert3, "https://www.ticketmaster.com/event/22005E38099ADD81");
        this.setLink(linkConcert4, "https://www.ticketmaster.com/event/0D005E331C2CFD6E");
        this.setLink(linkConcert5, "https://www.ticketmaster.com/event/3C005CD0E23C1499");

        ImageView prefences = findViewById(R.id.prefences);
        prefences.setClickable(true);
        prefences.bringToFront();

        if (musicaActivada) {
            mp = MediaPlayer.create(this, R.raw.melend);
            mp.start();
        }
        prefences.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(ListarConciertosActivity.this, PreferencesActivity.class);
                        startActivity(i);

                    }
                });


        back.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean musicaActivada = gbdRest.getPreferenciasMusica();
                        if (!musicaActivada) {
                            if (mp != null) {
                                mp.stop();
                            }

                        } else {
                            if (mp != null) {
                                mp.stop();
                                if (currentPositon == 0) {
                                    mp = MediaPlayer.create(ListarConciertosActivity.this, raws[raws.length - 1]);
                                    currentPositon = raws.length - 1;
                                } else {
                                    currentPositon--;
                                    mp = MediaPlayer.create(ListarConciertosActivity.this, raws[currentPositon]);
                                }
                                mp.start();
                            }

                        }
                    }
                });

        next.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean musicaActivada = gbdRest.getPreferenciasMusica();
                        if (!musicaActivada) {
                            if (mp != null) {
                                mp.stop();
                            }
                        } else {
                            if (mp != null) {
                                mp.stop();
                                if (currentPositon == raws.length - 1) {
                                    currentPositon = 0;
                                    mp = MediaPlayer.create(ListarConciertosActivity.this, raws[currentPositon]);
                                } else {
                                    currentPositon++;
                                    mp = MediaPlayer.create(ListarConciertosActivity.this, raws[currentPositon]);
                                }
                                mp.start();
                            }

                        }
                    }
                });

        restart.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean musicaActivada = gbdRest.getPreferenciasMusica();
                        if(!musicaActivada){
                            if(mp != null){
                                mp.stop();
                            }

                        }
                        if(mp != null){
                            mp.seekTo(0);
                        }

                    }
                });

        resume.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean musicaActivada = gbdRest.getPreferenciasMusica();
                        if (!musicaActivada) {
                            if (mp != null) {
                                mp.stop();
                            }
                        } else {
                            if (mp != null) {
                                if (mp.isPlaying()) {
                                    resume.setImageResource(R.drawable.start);
                                    resume.setTag("");
                                    mp.pause();
                                } else {
                                    resume.setTag("start");
                                    resume.setImageResource(R.drawable.pause);
                                    mp.start();
                                }
                            }

                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GestorPreferencias gbdRest = new GestorPreferencias(ListarConciertosActivity.this);
        boolean musicaActivada = gbdRest.getPreferenciasMusica();
        if (!musicaActivada) {
            if (mp != null) {
                mp.stop();
                resume.setTag("start");
            }
        } else {
            if (resume.getTag().equals("start")) {
                if(mp != null){
                    mp.start();
                    resume.setImageResource(R.drawable.pause);
                    if (!mp.isPlaying()) {
                        mp = MediaPlayer.create(this, R.raw.melend);
                        mp.start();
                    }
                }else{
                    mp = MediaPlayer.create(this, R.raw.melend);
                    mp.start();
                }

            }
        }

        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        if (mp != null) {
            mp.pause();

        }

        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        mAudioManager.abandonAudioFocus(this);

        super.onDestroy();

    }


    //IMPORTANT!!! Prémer el botó 'Back' en una activitat és equivalent al mètode onDestroy() i no permetrà la recuperació de les dades
    //Mètode per emmagatzemar la posició del MediaPlayer
    @Override
    protected void onSaveInstanceState(Bundle estadoGuardado) {
        super.onSaveInstanceState(estadoGuardado);
        if (mp != null) {
            int pos = mp.getCurrentPosition();
            estadoGuardado.putInt("posicion", pos);
        }
    }

    //Mètode per recuperar la posició del MediaPlayer
    @Override
    protected void onRestoreInstanceState(Bundle estadoGuardado) {
        super.onRestoreInstanceState(estadoGuardado);
        if (estadoGuardado != null && mp != null) {
            int pos = estadoGuardado.getInt("posicion");
            mp.seekTo(pos);

        }
    }

    @Override
    public void onAudioFocusChange(int i) {
        if (i <= 0) {
            if (mp != null) {
                mp.pause();
                posMusic = mp.getCurrentPosition();
            }
        } else {
            if (posMusic > 0 && mp != null) {
                mp.seekTo(posMusic);
                mp.start();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
