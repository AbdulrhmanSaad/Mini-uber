package com.example.uber;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportProblemFragment extends Fragment {
    RadioGroup radioGroup;
    AppCompatButton button;
    Complaint complaint;
    int trip_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_problem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        complaint = new Complaint();
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if (id == R.id.accidentproblem) {
                    complaint.setProblem("My driver was not wearing a face mask");
                } else if (id == R.id.maskproblem) {
                    complaint.setProblem("I lost an item");
                } else if (id == R.id.lostitemproblem) {
                    complaint.setProblem("I was involved in an accident");
                } else if (id == R.id.driverproblem) {
                    complaint.setProblem("My driver was rude or unprofessional");
                } else if (id == R.id.overchargeproblem) {
                    complaint.setProblem("I was overcharged on my cash trip");
                } else if (id == R.id.differentplateproblem)
                complaint.setProblem("The car had a different plate number");
            }
        });
        trip_id = 5;//(Integer) getIntent().getSerializableExtra("tripID");
        button = view.findViewById(R.id.submitproblem);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Complaints");

                complaint.setTripId(trip_id);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                String date = sdf.format(calendar.getTime());
                complaint.setDate(date);
                int b = (int) (Math.random());
                complaint.setComplaintID(b);
                myRef.push().setValue(complaint);
                Toast.makeText(getContext(), "Complaint Submitted", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(getContext(), "Trip ID: " + trip_id, Toast.LENGTH_SHORT).show();
    }

    }