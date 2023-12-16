package com.example.uber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    Handler handler ;
    LottieAnimationView lottieAnimationView;
    TextView appName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        appName=findViewById(R.id.appName);
        lottieAnimationView = findViewById(R.id.splashImage);
        lottieAnimationView.animate().translationY(1500).setDuration(1000).setStartDelay(5000);
        appName.animate().translationY(1500).setDuration(1000).setStartDelay(5000);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, OnBoardActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                finish();
            }
        },6000);
    }
}