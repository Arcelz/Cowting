package com.projetoifgoiano.cowting;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {


    private GoogleMap mMap;
    private String cordenada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        cordenada = intent.getStringExtra("Cordenada");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        FloatingActionButton inicia = (FloatingActionButton) findViewById(R.id.floatingActionButtonMaps);
        inicia.setOnClickListener(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng cord = null;
        if (cordenada == null){
           cord = new LatLng(-17.733114, -49.114595);
           mMap.addMarker(new MarkerOptions().position(cord).title("Marker"));
        }
        else {
            String corde[] = cordenada.split(",");
            int inc1=0;
            int inc2=1;
            for (int i=0;i <= corde.length;i++) {
                if(i+inc2 < corde.length){
                    cord = new LatLng(Double.valueOf(corde[i+inc1]),Double.valueOf(corde[i+inc2]));
                    mMap.addMarker(new MarkerOptions().position(cord).title("Marker"));
                    inc1 = 1;
                    inc2 = 2;
                }
            }
        }
        float zoomLevel = (float) 16.0;
        Log.d("LOGCOW",""+cord);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cord, zoomLevel));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title("New Marker");
                mMap.addMarker(marker);

            }

        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MapsActivity.this,OpcoesActivity.class);
        startActivity(intent);
    }
}
