package com.example.weiyi.dynamicnotification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNotificatoin = (Button) findViewById(R.id.btn_startNotification);
        btnNotificatoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startNotification:
                startNotification();
                break;
        }
    }

    @TargetApi(16)
    private void startNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager == null) {
            return;
        }
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.remoteview_layout);

        remoteViews.setViewVisibility(R.id.viewstub, View.VISIBLE);
        Notification notificationCompat = new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .build();
        manager.notify(0, notificationCompat);

    }
}
