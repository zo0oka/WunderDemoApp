package com.example.zo0okadev.wunderdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class SingleCarMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String carName = "car_name";
    public static final String carLng = "car_lng";
    public static final String carLat = "car_lat";
    public String name;
    public double lng;
    public double lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_car_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        name = Objects.requireNonNull(getIntent().getExtras()).getString(carName);
        lat = getIntent().getExtras().getDouble(carLng);
        lng = getIntent().getExtras().getDouble(carLat);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng marker = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(marker).title(name));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 12.0f));
    }
}
