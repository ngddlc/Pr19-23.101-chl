package com.example.pr19_23101_fi;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(requireActivity(), (view, hourOfDay, minute1) -> {
            @SuppressLint("DefaultLocale") String time = hourOfDay + ":" + String.format("%02d", minute1);
            ((MainActivity) requireActivity()).updateTextView("Выбрано время: " + time);
        }, hour, minute, true);
    }
}