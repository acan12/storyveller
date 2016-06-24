package com.xzone.app.storyveller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

/**
 * Created by arysuryawan on 4/1/16.
 */
public class ItienaryDetailActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_itienary_detail);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Toba Lake");
        setSupportActionBar(mToolbar);

        Log.d("", "==================== call notify");

        // show back actionbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bitmap remote_picture = null;

        // Creates an explicit intent for an ResultActivity to receive.
        Intent resultIntent = new Intent(this, TimelineDetailActivity.class);

// This ensures that the back button follows the recommended
// convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(TimelineDetailActivity.class);

// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
        notiStyle.setBigContentTitle("Big Picture Expanded");
        notiStyle.setSummaryText("Nice big picture!");
        notiStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.aurora_borealis));

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

// Create the final Notification object.
        Notification myNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.com_facebook_button_icon)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.aurora_borealis))
                .setContentIntent(resultPendingIntent)
                .setContentTitle("Normal Notification")
                .setContentText("This is an example of a Normal Style.")
                .setStyle(notiStyle)
                .addAction(R.drawable.ic_backspace_dark, "Prev", resultPendingIntent)
                .addAction(R.drawable.ic_check_light, "Pause", resultPendingIntent)
                .addAction(R.drawable.ic_map, "Pause", resultPendingIntent)
                .setSound(alarmSound)
                .build();


        int mNotificationId = 001;
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, myNotification);


//        Fragment fragment = new TimelineFragment();
//
//        if (fragment != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container_body, fragment);
//            fragmentTransaction.commit();
//
//        }
    }
}
