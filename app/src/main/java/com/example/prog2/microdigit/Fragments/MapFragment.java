package com.example.prog2.microdigit.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prog2.microdigit.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {


    private View rootView;
    private GoogleMap mMap;
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

        mMap = googleMap;

        //1 full mapa
        //5 nivel continente
        //10 nivel ciudad
        //15 nivel calle
        //20 nivel edificio (21 maximo)

        //acotar maximos y minimos (sino pones nada tienes all el abanico por defecto)

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(21);

        // Añadir marcador

        LatLng sydney = new LatLng(-34, 151);
        LatLng hayma = new LatLng(41.452237940530296,2.209321909764924);

        //añadios marcador y le decimos que sea draggable(true)

        mMap.addMarker(new MarkerOptions().position(hayma).title("Marcador de mi Hayma").draggable(true));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Esto es sidney bro").draggable(true));


        //Configurar la posicion por defecto de la camara al iniciar.

        CameraPosition camera = new CameraPosition.Builder()

                .target(hayma)
                .zoom(16)
                .bearing(90)
                .tilt(45)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));


        //Hacer click al mapa e interacturar (te devuelve latitud y long)

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(rootView.getContext(), "Click aqui: \n" +
                        "Lat: "+latLng.latitude+"\n"+
                        "Lon: "+latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        // Pulsar manteniendo

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                Toast.makeText(rootView.getContext(), "Largo Click aqui: \n" +
                        "Lat: "+latLng.latitude+"\n"+
                        "Lon: "+latLng.longitude, Toast.LENGTH_SHORT).show();


            }
        });
        //
        //arrastar y dejar en el mapa

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                Toast.makeText(rootView.getContext(), "Draggenado Click aqui: \n" +
                        "Lat: "+marker.getPosition().latitude+"\n"+
                        "Lon: "+marker.getPosition().longitude, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
