package com.example.android5778_7822_2864;


import android.Manifest;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//import static android.support.v4.content.PermissionChecker.checkSelfPermission;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSpinner extends Fragment implements AdapterView.OnItemSelectedListener {
    LocationListener locationListener;
    LocationManager locationManager;
   public static String location;

public String str []={"Available Travels","Distance","City","Payment amount"};
     FragmentManager fM;
     FragmentTransaction fT;

    public FragmentDefault fragment_default2 = new FragmentDefault();

    Long idOfDriver;
    public void setIdOfDriver(Long id){idOfDriver=id;}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fM = getFragmentManager();
        fT= fM.beginTransaction();
        View view= inflater.inflate(R.layout.fregment_spinner_available, container, false);
        Spinner spinner=view.findViewById(R.id.spinnerAvailable);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        return view;


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(str[position]=="Available Travels") {

            fM= getFragmentManager();
            fT= fM.beginTransaction();
            fT.replace(R.id.container2,fragment_default2 );

            FragmentAvailable fragment_aviliable=new FragmentAvailable();
            fragment_aviliable.setId(idOfDriver);
            fT.replace(R.id.fragment_Main,fragment_aviliable );
            fT.commit();
            replaceToFragmentDefault();
        }
       if(str[position]=="Distance") {

          // String location =
                   location();
          /* Dialog_Of_Spinner1 distance = new Dialog_Of_Spinner1();
           distance.setLocation(location);
           distance.show(getFragmentManager(), "Distance");
           replaceToFragmentDefault();*/
       }
        if(str[position]=="City")
        {
            Dialog_Of_spinner1City city=new Dialog_Of_spinner1City();
            city.show(getFragmentManager(),"City");
            replaceToFragmentDefault();
        }
        if(str[position]=="Payment amount")
        {
            Dialog_Of_Spinner1PayAmount distance=new Dialog_Of_Spinner1PayAmount();
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
    public void location() {

        try {

            locationListener = new LocationListener() {
                public void onLocationChanged(Location newlocation) {
                    // Called when a new location is found by the network location provider.
                    Dialog_Of_Spinner1 distance = new Dialog_Of_Spinner1();
                    distance.setLocation(getPlace(newlocation));
                    distance.show(getFragmentManager(), "Distance");
                    replaceToFragmentDefault();
                    // Remove the listener you previously added
                   // locationManager.removeUpdates(locationListener);


                }
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                   getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }
        catch (Exception ex){

        }
      //  return location;
    }




    public String getPlace(Location location) {

        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addresses.size() > 0) {
                String cityName = addresses.get(0).getAddressLine(0);
                //     String stateName = addresses.get(0).getAddressLine(1);
                //     String countryName = addresses.get(0).getAddressLine(2);
                return cityName + "\n";// + cityName + "\n" + countryName;
            }

            return "no place: \n ("+location.getLongitude()+" , "+location.getLatitude()+")";
        }
        catch(
                IOException e)

        {
            e.printStackTrace();
        }
        return "IOException ...";
    }

}
