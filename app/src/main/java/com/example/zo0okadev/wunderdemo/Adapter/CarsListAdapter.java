package com.example.zo0okadev.wunderdemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zo0okadev.wunderdemo.Model.Car;
import com.example.zo0okadev.wunderdemo.R;
import com.example.zo0okadev.wunderdemo.SingleCarMapsActivity;

import java.util.ArrayList;

public class CarsListAdapter extends BaseAdapter {

    private static final String carName = "car_name";
    private static final String carLng = "car_lng";
    private static final String carLat = "car_lat";
    private ArrayList<Car> cars;
    private Context context;

    public CarsListAdapter(Context context, ArrayList<Car> cars) {
        this.context = context;
        this.cars = cars;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cars_list_item, parent, false);
            final Car car = (Car) getItem(position);
            TextView carNameTextView = convertView.findViewById(R.id.carNameTextView);
            carNameTextView.setText(car.getCarName());
            ImageView locationImageView = convertView.findViewById(R.id.locationImageView);
            locationImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), SingleCarMapsActivity.class);
                    intent.putExtra(carName, car.getCarName());
                    intent.putExtra(carLng, car.getCarLat());
                    intent.putExtra(carLat, car.getCarLng());
                context.startActivity(intent);
                }
            });
            TextView carExteriorTextView = convertView.findViewById(R.id.carExteriorTextView);
            carExteriorTextView.setText(car.getCarExterior());
            TextView carInteriorTextView = convertView.findViewById(R.id.carInteriorTextView);
            carInteriorTextView.setText(car.getCarInterior());
            TextView carAddressTextView = convertView.findViewById(R.id.carAddressTextView);
            carAddressTextView.setText(car.getCarAddress());
        }
        return convertView;
    }
}
