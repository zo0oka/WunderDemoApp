package com.example.zo0okadev.wunderdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.zo0okadev.wunderdemo.Model.Car;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AllCarsMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ArrayList<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cars_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray placemarks = jsonObject.getJSONArray("placemarks");
            for (int i = 0; i < placemarks.length(); i++) {
                JSONObject object = placemarks.getJSONObject(i);
                JSONArray lngLat = object.getJSONArray("coordinates");
                double lat = lngLat.getDouble(0);
                double lng = lngLat.getDouble(1);
                String name = object.getString("name");

                Car car = new Car(name, lat, lng);
                cars.add(car);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for (int i = 0; i < cars.size(); i++) {
            LatLng carLatLng = new LatLng(cars.get(i).getCarLat(), cars.get(i).getCarLng());
            googleMap.addMarker(new MarkerOptions().position(carLatLng).title(cars.get(i).getCarName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carLatLng, 12.0f));
        }
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("locations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
