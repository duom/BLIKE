package com.example.prog2.microdigit.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prog2.microdigit.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {


    private View rootView;
    private GoogleMap gMap;
    private MapView mapView;

    public MapFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        return  rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) rootView.findViewById(R.id.map);

        if(mapView != null){

            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;

        gMap.setMinZoomPreference(10);
        gMap.setMaxZoomPreference(21);

        LatLng hayma = new LatLng(41.452237940530296,2.209321909764924);
        gMap.addMarker(new MarkerOptions().position(hayma).title("Marcador de mi Hayma").draggable(true));

        CameraPosition camera = new CameraPosition.Builder()

                .target(hayma)
                .zoom(16)
                .bearing(90)
                .tilt(45)
                .build();

        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));


    }
}
