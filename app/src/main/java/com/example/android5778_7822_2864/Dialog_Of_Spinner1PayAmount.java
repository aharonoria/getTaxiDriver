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

import java.text.ParseException;
import java.util.List;

public class Dialog_Of_Spinner1PayAmount  extends DialogFragment

{
    Long idOfDriver=new Long(-1);
    public void setIdOfDriver (Long idOfDriver1){idOfDriver=idOfDriver1;}
    @Override
    public Dialog onCreateDialog(Bundle SaveInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_pay, null);
        final EditText editText=view.findViewById(R.id.editTextPay);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Payment Amount")
                    .setView(view)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(editText.getText().toString().isEmpty())
                            {
                                editText.setError("insert pay");
                            }
                            else {
                                List<Travel> travels;
                                int pay = Integer.parseInt(editText.getText().toString());
                                travels = BackendFactory.getInstance(getActivity()).getTravelPay(pay, getActivity(), idOfDriver);
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment_My_Travels fragment_my_travels = new Fragment_My_Travels();
                                fragment_my_travels.setTravels(travels);
                                fragmentTransaction.replace(R.id.fragment_Main, fragment_my_travels);
                                fragmentTransaction.commit();
                               // replaceToFragmentDefault();
                            }
                        }
                    });
            return builder.create();

    }

/*    public void replaceToFragmentDefault()
    {
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        FragmentDefault fragmentDefault=new FragmentDefault();
        fragmentTransaction.replace(R.id.container2,fragmentDefault );
        fragmentTransaction.commit();

    }*/
}