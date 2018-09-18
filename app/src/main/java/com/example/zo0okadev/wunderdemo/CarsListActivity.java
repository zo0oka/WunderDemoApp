package com.example.zo0okadev.wunderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.zo0okadev.wunderdemo.Adapter.CarsListAdapter;
import com.example.zo0okadev.wunderdemo.Model.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CarsListActivity extends AppCompatActivity {

    private ArrayList<Car> cars = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cars_list:
                    Intent carsListIntent = new Intent(getApplicationContext(), CarsListActivity.class);
                    startActivity(carsListIntent);
                    return true;
                case R.id.cars_map:
                    Intent carsMapIntent = new Intent(getApplicationContext(), AllCarsMapsActivity.class);
                    startActivity(carsMapIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_list);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ListView listView = findViewById(R.id.carListView);

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray placemarks = jsonObject.getJSONArray("placemarks");
            for (int i = 0; i < placemarks.length(); i++) {
                JSONObject object = placemarks.getJSONObject(i);
                String address = object.getString("address");
                String exterior = object.getString("exterior");
                String interior = object.getString("interior");
                JSONArray lngLat = object.getJSONArray("coordinates");
                double lat = lngLat.getDouble(1);
                double lng = lngLat.getDouble(0);
                String name = object.getString("name");

                Car car = new Car(name, address, lng, lat, exterior, interior);
                cars.add(car);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            CarsListAdapter carsListAdapter = new CarsListAdapter(this, cars);
            listView.setAdapter(carsListAdapter);
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
