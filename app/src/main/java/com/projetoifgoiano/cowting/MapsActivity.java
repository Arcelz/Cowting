package com.projetoifgoiano.cowting;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.activity_maps, container, false);

        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);
        }

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng sydney = new LatLng(-17.733114, -49.114595);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Morrinhos"));
        float zoomLevel = (float) 16.0;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title("New Marker");
                map.addMarker(marker);

            }

        });
    }
}
