package com.example.sherryy.notificationsampleapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by sherryy on 4/3/17.
 */

public class NotificationIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public NotificationIntentService() {
        super("notificationIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        switch (intent.getAction()) {
            case "accept":
                Handler leftHandler = new Handler(Looper.getMainLooper());
                leftHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent1 = new Intent(getBaseContext(),TestActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        Toast.makeText(getBaseContext(), "You clicked the left button", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "reject":
                Handler rightHandler = new Handler(Looper.getMainLooper());
                rightHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent2 = new Intent(getBaseContext(),TestActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                        Toast.makeText(getBaseContext(), "You clicked the right button", Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }
}
