package com.example.pr19_23101_fi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements InputDialogFragment.OnDataSentListener {
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        showCurrentDateTime();

        Button btnDate = findViewById(R.id.btnDate);
        Button btnTime = findViewById(R.id.btnTime);
        Button btnInput = findViewById(R.id.btnInput);

        btnDate.setOnClickListener(v ->
                new DatePickerFragment().show(getSupportFragmentManager(), "datePicker"));
        btnTime.setOnClickListener(v ->
                new TimePickerFragment().show(getSupportFragmentManager(), "timePicker"));
        btnInput.setOnClickListener(v ->
                new InputDialogFragment().show(getSupportFragmentManager(), "inputDialog"));
    }

    private void showCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        tvResult.setText(sdf.format(calendar.getTime()));
    }

    public void updateTextView(String newText) {
        tvResult.setText(newText);
    }

    @Override
    public void onDataSent(String data) {
        tvResult.setText("Получено из диалога: " + data);
    }
}