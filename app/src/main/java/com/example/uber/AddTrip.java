package com.example.uber;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrip extends Fragment {
    EditText puckUpPoint,dest,time;
    AppCompatButton confirm;
    ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_trip, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String phoneNo = getArguments().getString("phone");
        initialize(view);
        if(phoneNo!=null) {
            confirm.setOnClickListener(item -> {
                progressBar.setVisibility(View.VISIBLE);
                String puckUp = puckUpPoint.getText().toString();
                String destination = dest.getText().toString();
                String t = time.getText().toString();
                Trip trip = new Trip(puckUp, destination, t,"",0.0f, phoneNo);
                addTrip(trip);
            });
        }
    }

    private void initialize(View v) {
        puckUpPoint=v.findViewById(R.id.pickupPoint);
        dest=v.findViewById(R.id.destination);
        time=v.findViewById(R.id.time);
        confirm=v.findViewById(R.id.confirm);
        progressBar=v.findViewById(R.id.progress_bar_add_trip);
    }
    public void addTrip(Trip trip) {

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Trips");
        String tripId = myRef.push().getKey(); // Get a unique key for the new trip
        myRef.child(tripId).setValue(trip).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "added successfully", Toast.LENGTH_SHORT).show();
                        // Data successfully added to Firebase
                        Log.d("Firebase", "Trip added successfully!");
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add data to Firebase
                        Log.e("Firebase", "Failed to add trip: " + e.getMessage());
                    }
                });

    }
}