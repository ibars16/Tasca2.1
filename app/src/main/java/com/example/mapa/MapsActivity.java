package com.example.mapa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] listaConciertos = {"Concert Miami-Dade Arena", "Dalas Texas Trust CU Theatre",
            "Houston Smart Financial Centre at Sugar Land", "Orlando Hard Rock Cafe", "New York Radio City Music Hall"};
    private double[] latitud = {25.78144485761386, 32.766965540758015, 29.577706843431965, 28.47382256163607, 40.76007349800751};
    private double[] longitud = {-80.18703873993636, -96.98219828312419, -95.64319833900385, -81.46755027340677, -73.97997720199308};
    private int id;
    private int[] image = {R.drawable.mele1, R.drawable.mele2, R.drawable.mele3, R.drawable.mele4, R.drawable.mele5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        this.id = getIntent().getExtras().getInt("id")-1;

        mapFragment.getMapAsync(this);

    }

    /**
     * Marcador a l'institut Jaume Huguet
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng concert = new LatLng(this.latitud[id], this.longitud[id]);   //Posicio de l'institut
        mMap.addMarker(new MarkerOptions().position(concert).title(this.listaConciertos[id]).icon(BitmapDescriptorFactory.fromResource(image[id])).snippet("asd"));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17.0f)); //Fixem el zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLng(concert));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setContentDescription("aedfasdf");


    }
}