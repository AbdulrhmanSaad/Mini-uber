package com.example.uber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ModuleSelectorActivity extends AppCompatActivity {
    private AppCompatButton driverButton;
    private AppCompatButton riderButton;
    private AppCompatButton employeeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDriverButton();
        setRiderButton();
        setEmployeeButton();
    }
    void setEmployeeButton() {
        employeeButton = findViewById(R.id.employeeButton);
        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModuleSelectorActivity.this, SignInActivity.class);
                intent.putExtra("Module Choice", ModuleOption.EMPLOYEE);
                Toast.makeText(ModuleSelectorActivity.this, String.valueOf(ModuleOption.EMPLOYEE), Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    void setRiderButton() {
        riderButton = findViewById(R.id.riderButton);
        riderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModuleSelectorActivity.this, SignInActivity.class);
                intent.putExtra("Module Choice",ModuleOption.RIDER);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    void setDriverButton() {
        driverButton = findViewById(R.id.driverButton);

        driverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModuleSelectorActivity.this, SignInActivity.class);

                intent.putExtra("Module Choice", ModuleOption.DRIVER);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}