package Classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Rental_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper=new NotificationHelper(context);
        NotificationCompat.Builder np=notificationHelper.getChannelNotification("Requset alarm","You have a new  requset");
        notificationHelper.getManager().notify(1,np.build());

    }
}
