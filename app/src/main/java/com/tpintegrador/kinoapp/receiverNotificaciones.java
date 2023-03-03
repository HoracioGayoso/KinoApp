package com.tpintegrador.kinoapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class receiverNotificaciones extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Intent destino = new Intent(context,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,destino,PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,
                ForoFragment.channelId)
                .setSmallIcon(R.drawable.circle_shape)
                .setContentTitle("Publicación realizada!")
                .setContentText("Tu comentario en el foro "+intent.getExtras().getString("nombreForo")+" fue publicado!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
        System.out.println("Se llega a hacer la notificacion \n");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        Notification notificacion = mBuilder.build();
        notificationManager.notify(1,notificacion);
    }
}
