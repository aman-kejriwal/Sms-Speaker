package com.example.smstovoiceapp;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Locale;

//aman kumar
public class YourService extends Service {
    MessageReceiver messageReceiver;
    TextToSpeech textToSpeech;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do your jobs here
        //
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(1000);
        MessageReceiver screenOnOffReceiver = new MessageReceiver();
        registerReceiver(screenOnOffReceiver, intentFilter);
        //
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, MainActivity.channelId)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My new notification")
                        .setContentText("aman");
        startForeground(100,notificationBuilder.build());
        //
        Toast.makeText(this,"BackGround Task starts", Toast.LENGTH_LONG).show();
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
}
