package us.allinvest.meizutest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.util.Log;

/**
 * Created by chenchao on 9/10/15.
 */
public class TestReceiver extends BroadcastReceiver {
    private static final String TAG = TestReceiver.class.getSimpleName();

    @Override public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "intent = " + intent);
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(intent.getStringExtra(Constants.Extra.TITLE))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(RingtoneManager.getDefaultUri(
                        RingtoneManager.TYPE_NOTIFICATION));
        notificationManager
                .notify(intent.getIntExtra(Constants.Extra.NOTIFICATION_ID, 0), builder.build());
    }
}
