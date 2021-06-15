package com.example.application.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.application.R;
import com.example.application.ui.base.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setContentTitle("선빵! 빵 판매 시각 알림")
                .setContentText(intent.getExtras().get("storeName").toString()+" "+intent.getExtras().get("foodName").toString()+" 판매 시작합니다.")
                .setSmallIcon(R.drawable.ic_food);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Integer.parseInt(intent.getExtras().get("foodSeq").toString()), notifyBuilder.build());

    }
}
