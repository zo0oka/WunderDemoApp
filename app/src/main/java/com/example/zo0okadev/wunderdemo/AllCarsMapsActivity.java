package com.example.zo0okadev.wunderdemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

    private GoogleMap mMap;
    private ArrayList<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cars_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray placemarks = jsonObject.getJSONArray("placemarks");
            for (int i = 0; i < placemarks.length(); i++) {
                JSONObject object = placemarks.getJSONObject(i);
                String address = object.getString("address");
                JSONArray lngLat = object.getJSONArray("coordinates");
                double lat = lngLat.getDouble(1);
                double lng = lngLat.getDouble(0);
                String name = object.getString("name");

                Car car = new Car(name, address, lat, lng);
                cars.add(car);
            }
        } catch (JSONException e){
            e.printStackTrace();
            }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i = 0; i < cars.size(); i++){
            LatLng carLatLng = new LatLng(cars.get(i).getLat(), cars.get(i).getLng());
            mMap.addMarker(new MarkerOptions().position(carLatLng).title(cars.get(i).getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(carLatLng));
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
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
