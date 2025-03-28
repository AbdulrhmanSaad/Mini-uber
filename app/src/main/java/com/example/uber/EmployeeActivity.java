package com.example.uber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EmployeeActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private final DatabaseReference empRef = FirebaseDatabase.getInstance().getReference().child("Employees");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String phoneNo=getIntent().getStringExtra("phoneNo");
        setContentView(R.layout.activity_employee);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.defaultBackground));
        initialize();

        View headerView = navigationView.getHeaderView(0);

        TextView employeeName = headerView.findViewById(R.id.nav_header_employee_name);

        empRef.addListenerForSingleValueEvent(new ValueEventListener() {

            Employee employee;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    employee = postSnapshot.getValue(Employee.class);
                    assert employee != null;
                    if (employee.getPhoneNumber().equals(phoneNo) ){
                        break;
                    }

                }
                employeeName.setText(employee.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

        navigationView.setNavigationItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_account_info){
                replaceFragment(new EmployeeInformationFragment());
            }
            else if(item.getItemId() == R.id.nav_sign_up_driver){
                replaceFragment(new SignUpDriverFragment());
            }
            else if(item.getItemId() == R.id.nav_add_car){

                replaceFragment(new AddCarFragment());
            }
            else if(item.getItemId() == R.id.nav_assign_car_to_driver){

                replaceFragment(new AssignCarToDriverFragment());
            }
            else if(item.getItemId() == R.id.nav_logout){
                //Take an action to sign out employee, won't need a fragment
                signOutEmployee();
            }
            else {
                replaceFragment(new AddCarFragment());
            }
            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        });


    }
    private void signOutEmployee(){
        Intent intent = new Intent(getBaseContext(), SignInActivity.class);
        intent.putExtra("Module Choice", ModuleOption.EMPLOYEE);
        startActivity(intent);
        finish();
    }
    private void initialize(){
        navigationView = findViewById(R.id.nav_view_employee);
        drawerLayout = findViewById(R.id.drawer_layout_employee);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_employee,fragment);
        transaction.commit();
    }
}