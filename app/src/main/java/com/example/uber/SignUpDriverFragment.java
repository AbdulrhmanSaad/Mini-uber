package com.example.uber;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Objects;

public class SignUpDriverFragment extends Fragment {

    private AppCompatEditText etName, etEmail, etDrivingLicense, etPhoneNo;
    private AppCompatButton signUp;
    private ProgressBar progressBar;
    private String name, email, drivingLicense, phoneNoWithoutCode;


    public SignUpDriverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_sign_up_driver, container, false);
        initialize(v);


        signUp.setOnClickListener(
                view -> {
                    convertEditTextsToStrings();

                    if (name.isEmpty() || email.isEmpty() || phoneNoWithoutCode.isEmpty() ||
                            drivingLicense.isEmpty()) {
                        Toast.makeText(v.getContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();
                    } else if (!FormatChecker.isValidEmail(email) || !FormatChecker.isValidPhoneNo(phoneNoWithoutCode)) {
                        Toast.makeText(v.getContext(), "your phone number or email has incorrect format"
                                , Toast.LENGTH_SHORT).show();
                    } else {
                        Driver driver1 = new Driver(name, email, phoneNoWithoutCode, 2.1f, drivingLicense,"");
                        progressBar.setVisibility(View.VISIBLE);
                        FireBaseChecker checker = new FireBaseChecker(ModuleOption.DRIVER, v.getContext(), progressBar);
                        User driver =  UserFactory.getUser("Driver",name,email,phoneNoWithoutCode);
                        driver1.setDrivingLicense(drivingLicense);
                        checker.checkIfUserHasAnExistingAccountUponSignUp(driver, () ->  addDriverDataToDatabase(driver1,v));

                    }
                });
        return v;

    }

    private void initialize(View v) {
        etName = v.findViewById(R.id.signUpNameDriver);
        etEmail = v.findViewById(R.id.signUpEmailDriver);
        etDrivingLicense = v.findViewById(R.id.signUpDriverLicense);
        etPhoneNo = v.findViewById(R.id.signUpPhoneNumberDriver);
        signUp = v.findViewById(R.id.btn_signupDriver);
        progressBar = v.findViewById(R.id.progress_bar_signup_driver);
    }

    private void convertEditTextsToStrings() {
        name =etName.getText().toString();
        email =etEmail.getText().toString();
        drivingLicense = etDrivingLicense.getText().toString();
        phoneNoWithoutCode =etPhoneNo.getText().toString();

    }

    private void clearInput() {
       etName.getText().clear();
        etEmail.getText().clear();
        etPhoneNo.getText().clear();
        etDrivingLicense.getText().clear();
    }
    public  void addDriverDataToDatabase(Driver driver,View v) {
        DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference().child("Drivers");

        driverRef.push().setValue(driver).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(v.getContext(), "Account Added Successfully", Toast.LENGTH_SHORT).show();
                clearInput();
            }
        });


    }
}