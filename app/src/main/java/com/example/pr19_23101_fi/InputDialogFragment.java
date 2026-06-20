package com.example.pr19_23101_fi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class InputDialogFragment extends DialogFragment {

    public interface OnDataSentListener {
        void onDataSent(String data);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText input = new EditText(requireContext());
        input.setHint("Введите ваше сообщение");
        input.setPadding(40, 40, 40, 40);

        return new AlertDialog.Builder(requireActivity())
                .setTitle("Передача данных")
                .setView(input)
                .setPositiveButton("Отправить", (dialog, which) -> {
                    if (getActivity() instanceof OnDataSentListener) {
                        ((OnDataSentListener) getActivity()).onDataSent(input.getText().toString());
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}