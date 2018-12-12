package com.example.prog2.microdigit.Fragments;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {


    private View rootView;
    private GoogleMap mMap;
    private MapView mapView;

    GoogleMap gMap;


    public MapFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) rootView.findViewById(R.id.map);

        if (mapView != null) {

            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //////////*********opcion 3 alberto********////////////
        gMap = googleMap;

        if (ActivityCompat.checkSelfPermission(MapFragment.this.getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapFragment.this.getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapFragment.this.getActivity(),new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            if(!gMap.isMyLocationEnabled())
                gMap.setMyLocationEnabled(true);

            LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
            Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (myLocation == null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                String provider = lm.getBestProvider(criteria, true);
                myLocation = lm.getLastKnownLocation(provider);
            }

            if(myLocation!=null){
                LatLng userLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 14), 1500, null);

            }
       }
//
        //1 full mapa
        //5 nivel continente
        //10 nivel ciudad
        //15 nivel calle
        //20 nivel edificio (21 maximo)

        //acotar maximos y minimos (sino pones nada tienes all el abanico por defecto)

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(21);

        //LatLng hayma = new LatLng(41.452237940530296, 2.209321909764924);

        LatLng inicio = new LatLng(41.4556390483911, 2.2162818178078396);
        LatLng sanJeronimo = new LatLng(41.490057, 2.223802);
        LatLng flow = new LatLng(41.487486, 2.227633);
        LatLng calDimoni = new LatLng(41.486315, 2.227802);
        LatLng mataMachos = new LatLng(41.488667, 2.227708);
        LatLng regueros = new LatLng(41.492123, 2.226508);

        //añadios marcador y le decimos que sea draggable(true)

        mMap.addMarker(new MarkerOptions()
                .position(sanJeronimo)
                .title("Arrastra a donde quieras proponer nueva ruta")
                .draggable(true));


        mMap.addMarker(new MarkerOptions()
                .position(flow)
                .title("Flow")
                .snippet("Descenso facil")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bike_downhillfacil))
                .draggable(false));

        mMap.addMarker(new MarkerOptions()
                .position(calDimoni)
                .title("Cal Dimoni")
                .snippet("Descenso dificil")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bike_downhilldificil))
                .draggable(false));

        mMap.addMarker(new MarkerOptions()
                .position(mataMachos)
                .title("Mata machos")
                .snippet("Ascenso muy dificil")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bike_risingdificil))
                .draggable(false));

        mMap.addMarker(new MarkerOptions()
                .position(regueros).title("Regueros")
                .snippet("Descenso muy dificl")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bike_downhilldificil))
                .draggable(false));

//        mMap.addMarker(new MarkerOptions().position(hayma).title("Abre caigut").draggable(true));
//        mMap.addMarker(new MarkerOptions().position(hayma).title("Clavicula").draggable(true));
//        mMap.addMarker(new MarkerOptions().position(hayma).title("4 Pussy").draggable(true));
//        mMap.addMarker(new MarkerOptions().position(hayma).title("Castillo").draggable(true));
//
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Esto es sidney bro").draggable(true));

        //Configurar la posicion por defecto de la camara al iniciar.

        CameraPosition camera = new CameraPosition.Builder()

                .target(inicio)
                .zoom(12)
                .bearing(90)
                .tilt(45)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

        //Hacer click al mapa e interacturar (te devuelve latitud y long)

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(rootView.getContext(), "BLIKER, este punto exacto es \n\n" +
                        "Lat: " + latLng.latitude + "\n" +
                        "Lon: " + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });
        // Pulsar manteniendo

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                Toast.makeText(rootView.getContext(), "Hecho BLIKER! Ruta guardada en Favoritos \n\n" +
                        "Lat: " + latLng.latitude + "\n" +
                        "Lon: " + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });
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


                Toast.makeText(rootView.getContext(), "Bien! Propuesta de ruta añadida en la siguiente latitud \n\n" +
                        "Lat: " + marker.getPosition().latitude + "\n" +
                        "Lon: " + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();


            }
        });



    }

}
