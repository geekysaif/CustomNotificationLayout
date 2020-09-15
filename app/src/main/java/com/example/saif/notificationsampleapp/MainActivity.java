package com.example.saif.notificationsampleapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    Button mButton;

    private static RemoteViews contentView;
    private static Notification notification;
    private static NotificationManager notificationManager;
    private static final int NotificationID = 1005;
    private static NotificationCompat.Builder mBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEditText.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter valid content", Toast.LENGTH_SHORT).show();
                    mEditText.setError("Please enter valid content");
                }else {
                    RunNotification();
                }

            }
        });
    }

    private void RunNotification() {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getApplicationContext(), "notify_001");

        contentView = new RemoteViews(getPackageName(), R.layout.notification_layout);
       // contentView.setImageViewResource(R.id.image, R.drawable.ic_banner);
        contentView.setTextViewText(R.id.title,  mEditText.getText().toString());

        Intent accept = new Intent(this, NotificationIntentService.class);
        accept.setAction("accept");
        contentView.setOnClickPendingIntent(R.id.flashButton, PendingIntent.getService(this, 0, accept, PendingIntent.FLAG_UPDATE_CURRENT));

        Intent reject = new Intent(this, NotificationIntentService.class);
        reject.setAction("reject");
        contentView.setOnClickPendingIntent(R.id.logoButton, PendingIntent.getService(this, 1, reject, PendingIntent.FLAG_UPDATE_CURRENT));

        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;
        mBuilder.setContent(contentView);
        mBuilder.setCustomBigContentView(contentView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        notification = mBuilder.build();
        notificationManager.notify(NotificationID, notification);
    }
}
