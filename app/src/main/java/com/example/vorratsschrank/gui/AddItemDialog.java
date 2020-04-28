package com.example.vorratsschrank.gui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.vorratsschrank.R;

public class AddItemDialog extends DialogFragment {

    private SaveItemListener listener = null;
    private View dialogContent = null;

    public interface SaveItemListener {
        void onItemSaved(String name, String ablaufDatum);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        dialogContent = inflater.inflate(R.layout.dialog_additem, null);
        builder.setView(dialogContent);

        builder.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    EditText textWidget = (EditText) dialogContent.findViewById(R.id.username);
                        if (!textWidget.getText().toString().equals("") && !textWidget.getText().toString().equals(null)) {
                            informListener();
                        }

                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
                .setTitle("Neuer Vorrat");
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void addSaveListener(SaveItemListener listener) {
        this.listener = listener;
    }

    private void informListener() {

        DatePicker picker = (DatePicker) dialogContent.findViewById(R.id.simpleDatePicker);
        String date = "" + String.format("%02d", picker.getDayOfMonth()) + "." + (String.format("%02d", picker.getMonth() + 1)) + "." + picker.getYear();
        EditText textWidget = (EditText) dialogContent.findViewById(R.id.username);

        this.listener.onItemSaved(textWidget.getText().toString(), date);
    }

}
