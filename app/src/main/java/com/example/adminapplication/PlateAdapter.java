package com.example.adminapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapplication.models.Car;

import java.util.List;

public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.ViewHolder> {
    private List<Car> cars;
    private Context context;
    private PlateAdapter.OnItemClickListener listener;


    public PlateAdapter(Context context, List<Car> cars, PlateAdapter.OnItemClickListener listener) {
        this.cars = cars;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public PlateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_listcar, parent, false);
        return new PlateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateAdapter.ViewHolder holder, int position) {
        holder.bind(cars.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        int id;
        TextView txtPlate;
        ImageView btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPlate = itemView.findViewById(R.id.txtPlate);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }

        public void bind(Car car, PlateAdapter.OnItemClickListener listener) {
            txtPlate.setText(car.getNumberPlate());
            id = car.getId();
            btnEdit.setOnClickListener(view -> listener.onItemClick(car));
        }

    }
    interface OnItemClickListener {
        void onItemClick(Car car);
    }
}
