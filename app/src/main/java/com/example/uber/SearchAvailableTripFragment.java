package com.example.uber;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchAvailableTripFragment extends Fragment {
    ArrayList<Trip> availableTrips;
    private final DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference().child("Trips");
    RecyclerView recyclerView;
    TripHistoryAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_available_trip, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        availableTrips=new ArrayList<>();
        recyclerView=view.findViewById(R.id.listTrip);
        tripRef.addListenerForSingleValueEvent(new ValueEventListener() {
            Trip trip;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    trip = postSnapshot.getValue(Trip.class);
                    assert trip != null;
                    if (trip.getDriver().getPhoneNumber().equals("")){
                        availableTrips.add(trip);
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new TripHistoryAdapter(availableTrips,2);
                    recyclerView.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}