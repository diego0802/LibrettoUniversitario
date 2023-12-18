package com.example.librettouniversitario;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    private Calendar calendar;
    private DatePicker datePicker;

    public static DatePickerFragment newInstance() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        Bundle args = new Bundle();

        datePickerFragment.setArguments(args);

        return datePickerFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        datePicker = new DatePicker(getActivity());
        calendar = Calendar.getInstance();

        return new AlertDialog.Builder(getActivity())
                .setView(datePicker)
                .setPositiveButton(R.string.alert_dialog_ok,
                        (dialog, whichButton) -> {
                            calendar.set(Calendar.YEAR, datePicker.getYear());
                            calendar.set(Calendar.MONTH, datePicker.getMonth());
                            calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());

                            // Verifica che l'anno sia maggiore o uguale a 2004
                            int year = calendar.get(Calendar.YEAR);
                            if (year > 2004) {
                                // Se l'anno è inferiore a 2004, mostra un messaggio di errore
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("L'anno di nascita deve essere minore o uguale a 2004")
                                        .setPositiveButton("OK", (dialogInterface, i) -> {
                                            // Aggiungi azioni aggiuntive se necessario
                                        })
                                        .create()
                                        .show();
                            } else {
                                // Se l'anno è corretto, chiama doPositiveClick()
                                ((RegistrazioneActivity) getActivity()).doPositiveClick(calendar);
                            }
                        })
                .setNegativeButton(R.string.alert_dialog_cancel,
                        (dialog, whichButton) -> {
                            dismiss(); // Chiude il dialog
                        })
                .create();
    }

}