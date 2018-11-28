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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {


    private View rootView;
    private GoogleMap mMap;
    private MapView mapView;
    //**op 3**//
    //GoogleMap gMap;
    ///***///


    //******opcion2******//
//    private Marker marcador;
//    double lat = 0.0;
//    double lng = 0.0;
    /////********///


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
        //gMap = googleMap;

//        if (ActivityCompat.checkSelfPermission(com.example.prog2.microdigit.Fragments.MapFragment.super.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MapsActivity.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        }else{
//            if(!gMap.isMyLocationEnabled())
//                gMap.setMyLocationEnabled(true);
//
//            LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//            Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//            if (myLocation == null) {
//                Criteria criteria = new Criteria();
//                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//                String provider = lm.getBestProvider(criteria, true);
//                myLocation = lm.getLastKnownLocation(provider);
//            }
//
//            if(myLocation!=null){
//                LatLng userLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
//                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 14), 1500, null);
//
//            }
//        }
        ///////***********************//////////

        //***opcion 2***//
        //miUbicacion();
          ///****///

        //1 full mapa
        //5 nivel continente
        //10 nivel ciudad
        //15 nivel calle
        //20 nivel edificio (21 maximo)

        //acotar maximos y minimos (sino pones nada tienes all el abanico por defecto)

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(21);

        // *****Opcion 1 para mostrar ubicación (solo este codigo)*****/////
//        if (ActivityCompat.checkSelfPermission(com.example.prog2.microdigit.Fragments.MapFragment.super.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(com.example.prog2.microdigit.Fragments.MapFragment.super.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
        ///////************////////

        // Añadir marcador

        //LatLng hayma = new LatLng(41.452237940530296, 2.209321909764924);
        LatLng sanJeronimo = new LatLng(41.490057, 2.223802);
        LatLng flow = new LatLng(41.487486, 2.227633);
        LatLng calDimoni = new LatLng(41.486315, 2.227802);
        LatLng mataMachos = new LatLng(41.488667, 2.227708);
        LatLng regueros = new LatLng(41.492123, 2.226508);


        //añadios marcador y le decimos que sea draggable(true)

//        mMap.addMarker(new MarkerOptions()
//                .position(hayma)
//                .title("Marcador de mi Hayma")
//                .draggable(true));

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
                .draggable(true));
//        mMap.addMarker(new MarkerOptions().position(hayma).title("Abre caigut").draggable(true));
//        mMap.addMarker(new MarkerOptions().position(hayma).title("Clavicula").draggable(true));
//        mMap.addMarker(new MarkerOptions().position(hayma).title("4 Pussy").draggable(true));
//        mMap.addMarker(new MarkerOptions().position(hayma).title("Castillo").draggable(true));
//


        //mMap.addMarker(new MarkerOptions().position(sydney).title("Esto es sidney bro").draggable(true));


        //Configurar la posicion por defecto de la camara al iniciar.

        CameraPosition camera = new CameraPosition.Builder()

                .target(sanJeronimo)
                .zoom(14)
                .bearing(90)
                .tilt(45)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));


        //Hacer click al mapa e interacturar (te devuelve latitud y long)

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(rootView.getContext(), "Click aqui: \n" +
                        "Lat: " + latLng.latitude + "\n" +
                        "Lon: " + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        // Pulsar manteniendo

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                Toast.makeText(rootView.getContext(), "Largo Click aqui: \n" +
                        "Lat: " + latLng.latitude + "\n" +
                        "Lon: " + latLng.longitude, Toast.LENGTH_SHORT).show();

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
                        "Lat: " + marker.getPosition().latitude + "\n" +
                        "Lon: " + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();

            }
        });

    }
    //// ***********Opcion 2 mostrar ubicacion me da probleas al final por el context..getSystemService********/////
//    private void agregarMarcador(double lat, double lng) {
//        LatLng coordenadas = new LatLng(lat, lng);
//        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
//        if (marcador != null) marcador.remove();
//        marcador = mMap.addMarker(new MarkerOptions()
//                .position(coordenadas)
//                .title("Mi posicion actual")
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
//        mMap.animateCamera(miUbicacion);
//    }
//
//    private void actualizarUbicacion(Location location) {
//        if (location != null) {
//            lat = location.getLatitude();
//            lng = location.getLongitude();
//            agregarMarcador(lat, lng);
//        }
//    }
//
//    LocationListener locListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            actualizarUbicacion(location);
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//    };
//
//    private void miUbicacion() {
//
//        if (ActivityCompat.checkSelfPermission(com.example.prog2.microdigit.Fragments.MapFragment.super.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(com.example.prog2.microdigit.Fragments.MapFragment.super.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        actualizarUbicacion(location);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locListener);
//
//    }

    //////////***********///////


}
