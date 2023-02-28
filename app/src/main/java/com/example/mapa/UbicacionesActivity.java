package com.example.mapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

public class UbicacionesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicaciones_activity);

        Button botonMiami = findViewById(R.id.concertMiami);
        Button botonDalas = findViewById(R.id.concertDlas);
        Button botonHouston = findViewById(R.id.concertHouston);
        Button botonOrlando = findViewById(R.id.concertOrlando);
        Button botonYork = findViewById(R.id.concertYork);

        botonMiami.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(UbicacionesActivity.this, MapsActivity.class);
                        i.putExtra("id", 1);
                        startActivity(i);
                    }
                });
        botonDalas.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(UbicacionesActivity.this, MapsActivity.class);
                        i.putExtra("id", 2);
                        startActivity(i);
                    }
                });
        botonHouston.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(UbicacionesActivity.this, MapsActivity.class);
                        i.putExtra("id", 3);
                        startActivity(i);
                    }
                });
        botonOrlando.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(UbicacionesActivity.this, MapsActivity.class);
                        i.putExtra("id", 4);
                        startActivity(i);
                    }
                });
        botonYork.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(UbicacionesActivity.this, MapsActivity.class);
                        i.putExtra("id", 5);
                        startActivity(i);
                    }
                });

    }
}
