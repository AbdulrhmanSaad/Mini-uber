package com.example.uber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripHistoryAdapter extends RecyclerView.Adapter<TripHistoryAdapter.ViewHolder> {
    ArrayList<Trip>trips;
    int selection;


    public TripHistoryAdapter(ArrayList<Trip> trips,int selection) {
        this.trips = trips;
        this.selection=selection;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.bind(trips.get(position));
    }

    @Override
    public int getItemCount() {
        return  trips == null ? 0 : trips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView riderPhone,driverPhone,pickUpPoint,destination,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            riderPhone=itemView.findViewById(R.id.rider_phone);
            driverPhone=itemView.findViewById(R.id.driver_phone);
            pickUpPoint=itemView.findViewById(R.id.pickupPoint);
            destination=itemView.findViewById(R.id.destination);
            time=itemView.findViewById(R.id.tripTime);
        }

        public void bind(Trip trip) {
            riderPhone.setText("Rider's Phone Number: "+trip.getRider().getPhoneNumber());
            driverPhone.setText("Driver's Phone Number: "+trip.getDriver().getPhoneNumber());
            pickUpPoint.setText("Pickup Point: "+trip.getPickUpPoint());
            destination.setText("Destination: "+trip.getDestination());
            time.setText("Time: "+trip.getTime());

        }
    }
}
