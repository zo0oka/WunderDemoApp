package com.example.zo0okadev.wunderdemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zo0okadev.wunderdemo.Model.Car;
import com.example.zo0okadev.wunderdemo.R;
import com.example.zo0okadev.wunderdemo.SingleCarMapsActivity;

import java.util.List;

public class CarsListAdapter extends RecyclerView.Adapter<CarsListAdapter.CarsViewHolder> {

    private Context context;
    private List<Car> cars;
    public static final String carName = "car_name";
    public static final String carLng = "car_lng";
    public static final String carLat = "car_lat";

    public CarsListAdapter (Context context, List<Car> cars){
        this.context = context;
        this.cars = cars;
    }
    @NonNull
    @Override
    public CarsListAdapter.CarsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cars_list_item, viewGroup, false);
        return new CarsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder carsViewHolder, final int i) {
        Car car = cars.get(i);
        carsViewHolder.name.setText(car.getName());
        carsViewHolder.address.setText(car.getAddress());
        carsViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), SingleCarMapsActivity.class);
                intent.putExtra(carName, cars.get(i).getName());
                intent.putExtra(carLng, cars.get(i).getLng());
                intent.putExtra(carLat, cars.get(i).getLat());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarsViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView address;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.carNameTextView);
            address = itemView.findViewById(R.id.carAddressTextView);
        }
    }
}
