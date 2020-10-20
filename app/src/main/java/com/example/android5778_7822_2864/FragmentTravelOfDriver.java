package com.example.android5778_7822_2864;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android5778_7822_2864.entities.Travel;
import com.example.android5778_7822_2864.entities.Travel.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTravelOfDriver extends Fragment {

    public Travel travel;

    /**
     * FragmentTravelOfDriver
     * defaulte constractor
     */
    public FragmentTravelOfDriver() {
        // Required empty public constructor
        travel=new Travel();
    }

    /**
     * FragmentTravelOfDriver
     * constractor
     * @param t
     */
    @SuppressLint("ValidFragment")
    public FragmentTravelOfDriver(Travel t) {
        travel=new Travel();
        // Required empty public constructor
        travel.setId(t.getId());
        travel.setIdOfDriver(t.getIdOfDriver());
        travel.setName(t.getName());
        travel.setDestination(t.getDestination());
        travel.setSource(t.getSource());
        travel.setPhone(t.getPhone());
        travel.setEmail(t.getEmail());
        travel.setFirstHour(t.getFirstHour());
        travel.setLastHour(t.getLastHour());
        travel.setMyTravelStatus(t.getMyTravelStatus());
    }


    /**
     * onCreateView
     * open fragment and applying value in view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_trvel_of_driver, container, false);
        //TextView textViewId= view.findViewById(R.id.textViewIdOfTravel);
        TextView textViewIdOfDriver= view.findViewById(R.id.textViewIdOfDriver);
        TextView textViewStatus= view.findViewById(R.id.textViewStatus);
        TextView textViewSource= view.findViewById(R.id.textViewSource);
        TextView textViewDestination= view.findViewById(R.id.textViewDestination);
        TextView textViewName= view.findViewById(R.id.textViewName);
        TextView textViewPhone= view.findViewById(R.id.textViewPhone);
        TextView textViewEmail= view.findViewById(R.id.textViewEmail);
        TextView textViewFirstHour= view.findViewById(R.id.textViewFirstHour);
        TextView textViewtextViewLastHour= view.findViewById(R.id.textViewLastHour);


        //textViewId.setText(travel.getId().toString());
        textViewIdOfDriver.setText(travel.getIdOfDriver().toString());
        textViewStatus.setText(travel.getMyTravelStatus().toString());
        textViewSource.setText(travel.getSource());
        textViewDestination.setText(travel.getDestination());
        textViewName.setText(travel.getName());
        textViewPhone.setText(travel.getPhone());
        textViewEmail.setText(travel.getEmail());
        textViewFirstHour.setText(travel.getFirstHour());
        textViewtextViewLastHour.setText(travel.getLastHour());
        // Inflate the layout for this fragment
        return view;

    }



}
