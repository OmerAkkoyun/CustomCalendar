package com.example.customcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnlibraryCalendar;
    private Button btnMyCustomCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        initWidgets();

        btnlibraryCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,CustomCalendarLibraryActivity.class);
            startActivity(intent);
        });

        btnMyCustomCalendar.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, MyCustomCalendarActivity.class);
            startActivity(intent);
        });


    }


    private void initWidgets() {
        btnlibraryCalendar = findViewById(R.id.btnLibraryCalendar);
        btnMyCustomCalendar = findViewById(R.id.btnMyCustomCalendar);

    }
}