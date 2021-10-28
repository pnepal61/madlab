package com.example.ex10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import android.app.TimePickerDialog;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    TimePicker alarmtime;

    TextClock currtime;
    TextView t;
    Button setAlarm,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_main );
        currtime = findViewById(R.id. currTime );
        setAlarm = findViewById(R.id. button );
        alarmtime = findViewById(R.id. timePicker );
        cancel = findViewById(R.id. cancel );
        t = findViewById(R.id. text );
        final AtomicInteger value = new AtomicInteger(0);
        final AtomicInteger stop = new AtomicInteger(0);
        final Timer timer = new Timer();
        final Ringtone r = RingtoneManager. getRingtone (getApplicationContext(),RingtoneManager. getDefaultUri (RingtoneManager.TYPE_ALARM ));
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                alarmtime.setVisibility(View. VISIBLE );
                stop.getAndSet(0);
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
//write if else to see if alarm time is same as currtime
                        Log. i ("here1",currtime.getText().toString());
                        Log. i ("here2",AlarmTime());
                        if(currtime.getText().toString().equals(AlarmTime())&&stop.get()==0){
                            Log. i ("here3","Works");
                            r.play();
                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(MainActivity.this)
                                            .setSmallIcon(R.mipmap.ic_launcher_round)
                                            .setContentTitle("Alarm App")
                                            .setContentText("Alarm is Ringing!");
                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                            {
                                String channelId = "notify_channel";
                                NotificationChannel channel = new NotificationChannel(
                                        channelId,
                                        "Notification channel",
                                        NotificationManager.IMPORTANCE_DEFAULT);
                                notificationManager.createNotificationChannel(channel);
                                mBuilder.setChannelId(channelId);
                            }
                            notificationManager.notify(1, mBuilder.build());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t.setText("ALARM IS RINGING");
                                }
                            });
                        }
                        else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t.setText("");
                                }
                            });
                            r.stop();

                        }
                    }
                },0,1000); //you want the delay to be zero because the task should be immediate and you want to check each second
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop.getAndSet(1);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(1);
            }
        });
    }
    public String AlarmTime(){
        Integer alarmHour = alarmtime.getHour();
        Integer alarmMin = alarmtime.getMinute();
        String alarm_time,end="AM",min,hr;
        if (alarmHour>=12){
            end = "PM";
            if (alarmHour!=12)
                alarmHour-=12;
        }
        if (alarmMin<10)
            min = "0"+String. valueOf (alarmMin);
        else{
            min = String. valueOf (alarmMin);
        }
        if (alarmHour==0)
            hr="12";
        else{
            hr = String. valueOf (alarmHour);
        }
        alarm_time = hr+":"+min+" "+end;
        return alarm_time;
    }
}