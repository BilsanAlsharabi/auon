package com.example.asus.placepicker;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.asus.placepicker.MainActivity;
import com.example.asus.placepicker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    //this class to interaction between service and acrivity to return to activity
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



       notifi();

        //active the service when it killed by another when it active
        return START_STICKY;

    }

    @Override
    public void onCreate() {
        super.onCreate();


    }



    private void notifi()
    {
        //responsibility of charastaristics
        NotificationCompat.Builder builder= new  NotificationCompat.Builder(this,"1");
        //set charastaristics
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Own" );
        builder.setContentText("Need help!");
        //vibrattion setting two vibration (vibration ,sleep)
        builder.setVibrate(new long[]{1000,1000,1000,1000});
        //color of notification
        builder.setLights(Color.BLUE,3000,3000);
        builder.setOnlyAlertOnce(true);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(Uri.parse(String.valueOf(alarmSound)));

        //intent we want to go on when press on button
        Intent notificationIntent = new Intent(this,VolunteerActivity.class);
        //link with intent
        PendingIntent contentIntent =PendingIntent.getActivity(this,0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        //Active the notification to appear in mobile channel
        NotificationManager manager=(  NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //apply all charastaristic of notifivation
        manager.notify(0,builder.build());

    }



}