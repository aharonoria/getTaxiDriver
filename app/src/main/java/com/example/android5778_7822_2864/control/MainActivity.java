package com.example.android5778_7822_2864.control;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android5778_7822_2864.FragmentAvailable;
import com.example.android5778_7822_2864.FragmentDefault;
import com.example.android5778_7822_2864.FragmentDrivers2;
import com.example.android5778_7822_2864.FragmentSpinner;
import com.example.android5778_7822_2864.FragmentSpinnerMyTravels;
import com.example.android5778_7822_2864.Fragment_My_Travels;
import com.example.android5778_7822_2864.R;
import com.example.android5778_7822_2864.model.backend.BackendFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   private Long idOfDriver;

    public FragmentDefault fragment_default = new FragmentDefault();
    public FragmentDefault fragment_default2 = new FragmentDefault();
    public FragmentDefault fragment_default3 = new FragmentDefault();

    public FragmentManager fM;
    public FragmentTransaction fT;
    static ComponentName service = null;
    /**
     * onCreate
     * add fragments, toolbar and navigation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intentFromLogin=getIntent();
        idOfDriver=intentFromLogin.getLongExtra(LoginActivity.ID_EXTRA,0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         fM= getFragmentManager();
         fT= fM.beginTransaction();

        fT.add(R.id.fragment_Main,fragment_default );
        fT.add(R.id.container2,fragment_default2 );
        fT.add(R.id.fragment_Spinner,fragment_default3);

        fT.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (service == null) {
            Intent intent = new Intent(getBaseContext(), NotificationService.class);
            service = startService(intent);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * onNavigationItemSelected
     * navigation and replace fragment
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        Fragment_My_Travels fragment_my_travels;

        FragmentSpinnerMyTravels fragmentSpinnerMyTravels;

        FragmentAvailable fragment_aviliable;
        if (id == R.id.nav_Travels) // selected my travels
        {
            fragmentTransaction.replace(R.id.container2,fragment_default2 );
            fragment_my_travels=new Fragment_My_Travels();

            fragmentSpinnerMyTravels=new FragmentSpinnerMyTravels();
            fragmentSpinnerMyTravels.setIdOfDriver(idOfDriver);

            fragmentTransaction.replace(R.id.fragment_Spinner,fragmentSpinnerMyTravels);

            fragment_my_travels.setId(idOfDriver);
            fragment_my_travels.setTravels(BackendFactory.getInstance(this).getAllMyTravels(idOfDriver));
            fragmentTransaction.replace(R.id.fragment_Main,fragment_my_travels );

            fragmentTransaction.commit();


        }
        else if (id == R.id.nav_Travel_available) // selected travel available
        {
            FragmentSpinner fragmentSpinner=new FragmentSpinner();
            fragmentManager= getFragmentManager();
            fragmentTransaction= fragmentManager.beginTransaction();
            fragmentSpinner.setIdOfDriver(idOfDriver);
            fragmentTransaction.replace(R.id.fragment_Spinner,fragmentSpinner);

            fragmentTransaction.replace(R.id.container2,fragment_default2 );
            fragment_aviliable=new FragmentAvailable();
            fragment_aviliable.setId(idOfDriver);
            fragmentTransaction.replace(R.id.fragment_Main,fragment_aviliable );
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_Exit) // selected exit
        {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else if (id == R.id.nav_all_Dirver) {
            try {
                FragmentDrivers2 fragmentDrivers = new FragmentDrivers2();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
           /* fragmentSpinner=new FragmentSpinner();
            fragmentTransaction.replace(R.id.fragment_Spinner,fragmentSpinner);*/

                //fTSpinnerTravel.commit();
                fragmentTransaction.replace(R.id.fragment_Main, fragmentDrivers);
                fragmentTransaction.replace(R.id.container2, fragment_default2);

                fragmentTransaction.commit();
            } catch (Exception ex) {

            }
        }
            else if (id == R.id.nav_Website)
        {
             Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://gett.com/il/about/"));
             startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
