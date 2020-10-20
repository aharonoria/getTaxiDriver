package com.example.android5778_7822_2864;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.widget.DatePicker;

import com.example.android5778_7822_2864.entities.Travel;
import com.example.android5778_7822_2864.model.backend.Backend;
import com.example.android5778_7822_2864.model.backend.BackendFactory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Dialog_Of_Spinner1Date  extends DialogFragment   implements DatePickerDialog.OnDateSetListener {
    Long idOfDriver=new Long(-1);
    void setIdOfDriver (Long id){idOfDriver=id;}

    public Dialog onCreateDialog(Bundle SaveInstanceState ) {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), R.style.AppTheme,this,year,month,day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
       // Date date=new Date(year-1900,month,dayOfMonth);
        String dateStr=dayOfMonth+"."+month+1+"."+year;
        List<Travel> travels;
        try {

            travels= BackendFactory.getInstance(getActivity()).getTravelInDate(dateStr,idOfDriver);
           // FragmentManager fM2=getFragmentManager();
            //FragmentTransaction fT2=fM2.beginTransaction();
            FragmentManager fragmentManager= getFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
            Fragment_My_Travels fragment_my_travels = new Fragment_My_Travels();
            fragment_my_travels.setTravels(travels);
            fragmentTransaction.replace(R.id.fragment_Main,fragment_my_travels );
            fragmentTransaction.commit();
          //  replaceToFragmentDefault();

        } catch (ParseException e) {
            e.printStackTrace();
        }



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
