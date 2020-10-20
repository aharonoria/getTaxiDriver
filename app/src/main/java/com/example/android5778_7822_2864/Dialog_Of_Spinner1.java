package com.example.android5778_7822_2864;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android5778_7822_2864.entities.Travel;
import com.example.android5778_7822_2864.model.backend.BackendFactory;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Dialog_Of_Spinner1 extends DialogFragment {

    public String location;

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public Dialog onCreateDialog(Bundle SaveInstanceState ) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_distance, null);
        final EditText editText = view.findViewById(R.id.editTextDistance);
        final TextView TextViewLocation=view.findViewById(R.id.TextViewLocation);

        TextViewLocation.setText(location);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Distance")
                    .setView(view)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (editText.getText().toString().isEmpty()) {
                                editText.setError("the filed is empty!");
                            } else {
                                int dis = Integer.parseInt(editText.getText().toString());
                                    try {

                                        List<Travel> travelList =
                                                BackendFactory.getInstance(getActivity()).getTravelNotTokenDestination(dis, location, getActivity());
                                        FragmentManager fragmentManager = getFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        Fragment_My_Travels fragment_my_travels = new Fragment_My_Travels();
                                        fragment_my_travels.setTravels(travelList);
                                        fragmentTransaction.replace(R.id.fragment_Main, fragment_my_travels);
                                        fragmentTransaction.commit();
                                    } catch (IOException e) {
                                        e.printStackTrace();

                                    }


                            }
                        }
                    });

            return builder.create();

    }

}
