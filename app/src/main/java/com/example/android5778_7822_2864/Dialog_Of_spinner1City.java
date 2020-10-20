package com.example.android5778_7822_2864;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.android5778_7822_2864.entities.Travel;
import com.example.android5778_7822_2864.model.backend.BackendFactory;

import java.util.List;

public class Dialog_Of_spinner1City  extends DialogFragment

    {
        public String city1;
        @Override
        public Dialog onCreateDialog(Bundle SaveInstanceState ) {

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_city, null);
            final EditText editText = view.findViewById(R.id.editTextCity);


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("City")
                    .setView(view)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            city1 = editText.getText().toString();
                            if (city1.isEmpty()) {
                                editText.setError("insert city!");
                            } else {
                                List<Travel> travels = BackendFactory.getInstance(getActivity()).getTravelNotTokenInCity(city1, getActivity());
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment_My_Travels fragment_my_travels = new Fragment_My_Travels();
                                fragment_my_travels.setTravels(travels);
                                fragmentTransaction.replace(R.id.fragment_Main, fragment_my_travels);
                                fragmentTransaction.commit();
                             //   replaceToFragmentDefault();

                            }
                        }
                    });


            return builder.create();
        }
      /*  public void replaceToFragmentDefault()
        {
            FragmentManager fragmentManager= getFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
            FragmentDefault fragmentDefault=new FragmentDefault();
            fragmentTransaction.replace(R.id.container2,fragmentDefault );
            fragmentTransaction.commit();

        }*/
}
