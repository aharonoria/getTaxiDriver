package com.example.android5778_7822_2864.model.backend;
import android.app.Notification;
import android.content.Context;
import android.location.Location;

import com.example.android5778_7822_2864.entities.Driver;
import com.example.android5778_7822_2864.entities.Travel;
import com.example.android5778_7822_2864.model.dataSource.DatabaseFB;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

// interface backend
public interface Backend {
    public interface Action<L extends Number> {
        void onSuccess(Long id);//what to do when the action success

        void onFailure(Exception exception);//what to do if its fail

        void onProgress(String status, double percent);//what to do when thus in progress
    }

    public interface NotifyDataChange<T> {

        void onDataChanged(T obj);

        void onFailure(Exception exception);
    }


    void addDriver(final Driver driver, Action action) throws Exception;

    List<Travel> getAllMyTravels(Long id);
    List<String> getAllNameDriver();
    List<Travel> getTravelNotTaken();
    List<Travel> getTravelEnd(Long idOfDriver);
    List<Travel> getTravelNotTokenInCity(String city,Context context);
    List<Travel> getTravelNotTokenDestination(float destination,String location,Context context) throws IOException;
    List<Travel> getTravelInDate(String date,Long idOfDriver) throws ParseException;
    List<Travel> getTravelPay(int pay,Context context,Long idOfDriver);
    List<Travel> getTravelList();
    List<Driver> getDriverList();
    void takeTravel(Travel travel, long idOfTravel);
    void finishTravel(Travel travel);


}