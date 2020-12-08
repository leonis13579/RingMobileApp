package ru.realityfamily.ringapp.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.ringapp.Models.CloudObject;
import ru.realityfamily.ringapp.Network.SingletonRetrofit;
import ru.realityfamily.ringapp.R;

public class NotifyService extends Service {
    MainThread mainThread;
    Context context;

    @Override
    public void onCreate() {
        mainThread = new MainThread();
        mainThread.setRunning(true);
        mainThread.start();

        context = this;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mainThread.setRunning(false);
    }


    public class MainThread extends Thread {
        boolean running = false;
        Map<CloudObject, Boolean> notifyThrown = new HashMap<>();

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            // The id of the channel.
            String id = "my_channel_01";
            String name = "channel for Ring notify";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            notificationManager.createNotificationChannel(mChannel);

            while (running) {
                SingletonRetrofit.getINSTANCE().getRest().getAllObjects().enqueue(new Callback<List<CloudObject>>() {
                    @Override
                    public void onResponse(Call<List<CloudObject>> call, Response<List<CloudObject>> response) {
                        for (CloudObject co : response.body()) {
                            notifyThrown.put(co, co.getState().isRing());
                        }

                        for (CloudObject co : notifyThrown.keySet()) {
                            if (notifyThrown.get(co)) {
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "")
                                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                                        .setContentTitle("Вам звонят в звонок " + co.getName())
                                        .setContentText("Может быть стоит посмотреть, кто там?")
                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                                        .setChannelId(id);

                                notificationManager.notify(0, builder.build());

                                notifyThrown.put(co, false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CloudObject>> call, Throwable t) {

                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}