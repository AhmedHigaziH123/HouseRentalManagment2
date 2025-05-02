package Classes;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.core.app.NotificationCompat;

import com.example.houserentalmanagment.R;
import com.google.android.gms.common.wrappers.Wrappers;

public class NotificationHelper extends ContextWrapper {
    public static final String ChannelId="ChannelId";
    public static final String ChannelName="ChannelName";
    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        CreatNotification();
    }

    private void CreatNotification() {
        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.O){
            NotificationChannel Channel=new NotificationChannel(ChannelId,ChannelName,NotificationManager.IMPORTANCE_DEFAULT);
            Channel.enableLights(true);
            Channel.enableVibration(true);
            Channel.setLightColor(R.color.KingBlue);
            Channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(Channel);
        }
    }
    public NotificationManager getManager(){
        if(manager==null){
            manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }
    public NotificationCompat.Builder getChannelNotification(String title, String Message){
        return new NotificationCompat.Builder(getApplicationContext(),ChannelId)
                .setContentTitle(title)
                .setContentText(Message)
                .setSmallIcon(R.drawable.ic_alarm)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


    }
}
