package com.example.weiyi.dynamicnotification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNotificatoinText = (Button) findViewById(R.id.btn_startNotification_textView);
        Button btnNotificationImage = (Button) findViewById(R.id.btn_startNotification_imageView);
        btnNotificatoinText.setOnClickListener(this);
        btnNotificationImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startNotification_textView:
                startNotification(R.layout.dynamic_textview_layout);
                break;
            case R.id.btn_startNotification_imageView:
                startNotification(R.layout.dynamic_imageview_layout);
        }
    }

    @TargetApi(16)
    private void startNotification(@LayoutRes int type) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager == null) {
            return;
        }
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.remoteview_layout);

//        remoteViews.setTextViewText(R.id.dynamic_remote_text, "TextOther");

//        ViewStub stub;
//        stub.setLayoutResource();
//        ImageView view;
//        view.setBackgroundColor();
//        view.setImageBitmap();
//        remoteViews.setInt(R.id.viewstub, "setLayoutResource", type);
//        remoteViews.setInt(R.id.dynamic_remote_image, "setBackgroundColor", Color.BLACK);
//        remoteViews.setViewVisibility(R.id.viewstub, View.VISIBLE);

        remoteViews.setInt(R.id.dynamic_remote_image, "setBackgroundColor", Color.BLACK);
        remoteViews.setBitmap(R.id.dynamic_remote_image, "setImageBitmap", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        remoteViews.setViewVisibility(R.id.dynamic_remote_image, View.VISIBLE);
        remoteViews.setViewVisibility(R.id.dynamic_remote_text, View.INVISIBLE);


        Notification notificationCompat = new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .build();
        manager.notify(0, notificationCompat);

    }
}
