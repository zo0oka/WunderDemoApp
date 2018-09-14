package com.example.zo0okadev.wunderdemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SingleCarMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = "SingleCarMapsActivity";
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
        mapFragment.getMapAsync(this);

        name = getIntent().getExtras().getString(carName);
        lat = getIntent().getExtras().getDouble(carLat);
        lng = getIntent().getExtras().getDouble(carLng);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng marker = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(marker).title(name));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 12.0f));
    }
}
