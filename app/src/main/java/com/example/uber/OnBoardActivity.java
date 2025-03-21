package com.example.uber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OnBoardActivity extends AppCompatActivity {
    ViewPager onBoardViewPager;
    SliderAdapter sliderAdapter;
    AppCompatButton onBoardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        setAdapter();
    }
    private void setAdapter() {
        onBoardViewPager = findViewById(R.id.onBoardViewPager);
        sliderAdapter = new SliderAdapter(this);
        onBoardViewPager.setAdapter(sliderAdapter);
        onBoardButton = findViewById(R.id.onBoardButton);
        if (onBoardViewPager.getCurrentItem() == 0) {
            onBoardButton.setText("Next");
        }
        onBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBoardViewPager.getCurrentItem() != 2) {
                    onBoardViewPager.setCurrentItem(onBoardViewPager.getCurrentItem() + 1, true);

                }


            }
        });
        onBoardViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (onBoardViewPager.getCurrentItem() == 2) {
                    onBoardButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(OnBoardActivity.this, ModuleSelectorActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                onBoardViewPager.setCurrentItem(onBoardViewPager.getCurrentItem(), true);
                if (onBoardViewPager.getCurrentItem() == 1) {
                    onBoardButton.setText("Next");
                }
                if (onBoardViewPager.getCurrentItem() == 0) {
                    onBoardButton.setText("Next");
                }
                if (onBoardViewPager.getCurrentItem() == 2) {
                    onBoardButton.setText("Finish");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}