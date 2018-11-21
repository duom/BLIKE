package com.example.prog2.microdigit.Activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.prog2.microdigit.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
                Toast.makeText(MapsActivity.this, "Click aqui: \n" +
                        "Lat: "+latLng.latitude+"\n"+
                        "Lon: "+latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        // Pulsar manteniendo

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                Toast.makeText(MapsActivity.this, "Largo Click aqui: \n" +
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

                Toast.makeText(MapsActivity.this, "Draggenado Click aqui: \n" +
                        "Lat: "+marker.getPosition().latitude+"\n"+
                        "Lon: "+marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
                
            }
        });

    }
}
