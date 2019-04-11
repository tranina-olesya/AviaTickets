package ru.vsu.aviatickets.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ui.ticketresults.TripActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonTips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonTips = (Button) findViewById(R.id.buttonTips);
        Intent intent = new Intent(MainActivity.this, TripActivity.class);
        buttonTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
