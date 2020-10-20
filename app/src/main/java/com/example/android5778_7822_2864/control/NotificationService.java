package com.example.android5778_7822_2864.control;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.example.android5778_7822_2864.control.NotificationReceiver;
import com.example.android5778_7822_2864.entities.Travel;
import com.example.android5778_7822_2864.model.backend.BackendFactory;
import com.example.android5778_7822_2864.model.dataSource.DatabaseFB;

import java.util.List;

public class NotificationService extends Service {
    private int lastCount = 0;
    Context context;
    DatabaseFB dbManager;

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        dbManager = (DatabaseFB) BackendFactory.getInstance(context);
        context = getApplicationContext();
        dbManager.notifyToTravelList(new DatabaseFB.NotifyDataChange<List<Travel>>() {
            @Override
            public void onDataChanged(List<Travel> obj) {
                try {
                    Intent intent = new Intent(context, NotificationReceiver.class);
                    sendBroadcast(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Exception exception) {
            }
        });
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
