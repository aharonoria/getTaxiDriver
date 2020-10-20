package com.example.android5778_7822_2864;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.android5778_7822_2864.model.backend.BackendFactory;


public class FragmentSpinnerMyTravels extends Fragment implements AdapterView.OnItemSelectedListener {
    public String str []={"My Travels","Travel completed","Date","Payment amount"};
     FragmentManager fragmentManager;
     FragmentTransaction fragmentTransaction;
    Long idOfDriver;
    public void setIdOfDriver(Long id){idOfDriver=id;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_spinner_my_travels, container, false);
        Spinner spinner=view.findViewById(R.id.spinnerMyTravels);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,str);
        fragmentManager= getFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(str[position]=="My Travels") {
            Fragment_My_Travels fragment_my_travels = new Fragment_My_Travels();
            fragmentManager= getFragmentManager();
            fragmentTransaction= fragmentManager.beginTransaction();
            fragment_my_travels.setId(idOfDriver);
            fragment_my_travels.setTravels(BackendFactory.getInstance(getActivity()).getAllMyTravels(idOfDriver));
            fragmentTransaction.replace(R.id.fragment_Main,fragment_my_travels );
            fragmentTransaction.commit();
            replaceToFragmentDefault();

        }
        if(str[position]=="Travel completed")
        {
            fragmentManager= getFragmentManager();
            fragmentTransaction= fragmentManager.beginTransaction();
            Fragment_My_Travels fragment_my_travels = new Fragment_My_Travels();
            fragment_my_travels.setTravels(BackendFactory.getInstance(getActivity()).getTravelEnd(idOfDriver));
            fragmentTransaction.replace(R.id.fragment_Main,fragment_my_travels);
            fragmentTransaction.commit();
            replaceToFragmentDefault();


        }
        if(str[position]=="Date")
        {
            Dialog_Of_Spinner1Date datePicker=new Dialog_Of_Spinner1Date();
            datePicker.setIdOfDriver(idOfDriver);
            datePicker.show(getFragmentManager(),"Select Date");
            replaceToFragmentDefault();

        }
        if(str[position]=="Payment amount")
        {
            Dialog_Of_Spinner1PayAmount distance=new Dialog_Of_Spinner1PayAmount();
            distance.setIdOfDriver(idOfDriver);
            distance.show(getFragmentManager(),"Payment amount");
            replaceToFragmentDefault();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {   }
    public void replaceToFragmentDefault()
    {
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        FragmentDefault fragmentDefault=new FragmentDefault();
        fragmentTransaction.replace(R.id.container2,fragmentDefault );
        fragmentTransaction.commit();

    }

}

