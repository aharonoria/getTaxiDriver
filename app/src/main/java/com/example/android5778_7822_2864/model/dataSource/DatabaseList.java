package com.example.android5778_7822_2864.model.dataSource;

import android.content.Context;
import android.location.Location;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.android5778_7822_2864.entities.Driver;
import com.example.android5778_7822_2864.entities.Travel;
import com.example.android5778_7822_2864.model.backend.Backend;

// a database class that implements the backend in list
public class DatabaseList implements com.example.android5778_7822_2864.model.backend.Backend {


    @Override
    public void addDriver(Driver driver, Action action) throws Exception {

    }

    @Override
    public List<Travel> getAllMyTravels(Long id) {
        return null;
    }

    @Override
    public List<String> getAllNameDriver() {
        return null;
    }

    @Override
    public List<Travel> getTravelNotTaken() {
        return null;
    }

    @Override
    public List<Travel> getTravelEnd(Long idOfDriver) {
        return null;
    }

    @Override
    public List<Travel> getTravelNotTokenInCity(String city, Context context) {
        return null;
    }

    @Override
    public List<Travel> getTravelNotTokenDestination(float destination, String location, Context context) throws IOException {
        return null;
    }

    @Override
    public List<Travel> getTravelInDate(String date, Long idOfDriver) throws ParseException {
        return null;
    }



    @Override
    public List<Travel> getTravelPay(int pay, Context context, Long idOfDriver) {
        return null;
    }

    @Override
    public List<Travel> getTravelList() {
        return null;
    }

    @Override
    public List<Driver> getDriverList() {
        return null;
    }

    @Override
    public void takeTravel(Travel travel, long idOfTravel) {

    }

    @Override
    public void finishTravel(Travel travel) {

    }
}
