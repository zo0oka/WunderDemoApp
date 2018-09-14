package com.example.zo0okadev.wunderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.zo0okadev.wunderdemo.Adapter.CarsListAdapter;
import com.example.zo0okadev.wunderdemo.Model.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CarsListActivity extends AppCompatActivity {

    private static final String TAG = "CarsListActivity";
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
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            CarsListAdapter carsListAdapter = new CarsListAdapter(this, cars);
            recyclerView.setAdapter(carsListAdapter);
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
