package com.example.uber;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class ViewPastTrips extends Fragment {
    private final DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference().child("Trips");
ArrayList<Trip>tripsHistory;
RecyclerView recyclerView;
TripHistoryAdapter adapter;
    String phoneNo="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         phoneNo = getArguments().getString("phone");
        return inflater.inflate(R.layout.fragment_view_past_trips, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tripsHistory=new ArrayList<>();
        recyclerView=view.findViewById(R.id.listTrip);
       final int userType=getArguments().getInt("userType");
        tripRef.addListenerForSingleValueEvent(new ValueEventListener() {
            Trip trip;
            String searchPhone;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    trip = postSnapshot.getValue(Trip.class);
                    assert trip != null;
                    if(userType==2)
                        searchPhone=trip.getRider().getPhoneNumber();
                    else if(userType==3)
                        searchPhone=trip.getDriver().getPhoneNumber();
                    if (searchPhone.equals(phoneNo) ){
                     tripsHistory.add(trip);
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new TripHistoryAdapter(tripsHistory,1);
                    recyclerView.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}