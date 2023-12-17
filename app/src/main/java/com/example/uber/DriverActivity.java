package com.example.uber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    String phoneNo;
    private final DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference().child("Drivers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        phoneNo=getIntent().getStringExtra("phoneNo");

        initialize();
        View headerView = navigationView.getHeaderView(0);

        TextView riderName = headerView.findViewById(R.id.nav_header_employee_name);
        driverRef.addListenerForSingleValueEvent(new ValueEventListener() {

            Driver driver;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    driver = postSnapshot.getValue(Driver.class);
                    assert driver != null;
                    if (driver.getPhoneNumber().equals(phoneNo) ){
                        break;
                    }

                }
                riderName.setText(driver.getName());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

        Bundle bundle = new Bundle();
        bundle.putString("phone",phoneNo);
        bundle.putInt("userType",ModuleOption.RIDER);
        navigationView.setNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.navhome){
                SearchAvailableTripFragment search=new SearchAvailableTripFragment();
                search.setArguments(bundle);
                replaceFragment(search);
            }
            else if(item.getItemId() == R.id.navtrips){
                ViewPastTrips viewPastTrips=new ViewPastTrips();
                viewPastTrips.setArguments(bundle);
                replaceFragment(viewPastTrips);
            }
            else if(item.getItemId() == R.id.navspersonalinfo){

                replaceFragment(new AddCarFragment());
            }
            else if(item.getItemId() == R.id.rate){

                replaceFragment(new AssignCarToDriverFragment());
            }
            else if(item.getItemId() == R.id.compliment){

                replaceFragment(new AssignCarToDriverFragment());
            }
            else if(item.getItemId() == R.id.navlogOut){
                signOutDriver();
            }
            else {
                signOutDriver();
            }
            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        });
    }
        private void signOutDriver(){
            Intent intent = new Intent(getBaseContext(), SignInActivity.class);
            intent.putExtra("Module Choice", ModuleOption.RIDER);
            startActivity(intent);
            finish();
        }
        private void initialize(){
            navigationView = findViewById(R.id.nav_view_rider);
            drawerLayout = findViewById(R.id.drawer_layout_rider);
        }
        private void replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container_rider,fragment);
            transaction.commit();
        }
}