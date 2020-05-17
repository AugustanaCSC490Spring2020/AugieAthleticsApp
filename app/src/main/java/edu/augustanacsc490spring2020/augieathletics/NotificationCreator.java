package edu.augustanacsc490spring2020.augieathletics;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.augustanacsc490spring2020.augieathletics.UpcomingGms.UpcomingGms;

import static edu.augustanacsc490spring2020.augieathletics.MainActivity.CHANNEL_ID;

// Class created to handle creating notifications for upcoming games
public class NotificationCreator extends BroadcastReceiver {

    private static final long TWO_HOURS_IN_MILLIS = 2*60*60*1000;

    //Majority of code pulled from https://developer.android.com/training/notify-user/build-notification
    public static void createNotif(Context source, String notifTitle, String notifContent, String gameTime, String gameDate) throws ParseException {
        // Adds functionality when notification is Clicked
        PendingIntent pendingIntent = sendUserToOnClick(source, UpcomingGms.class);

        // Calculates send time in Millis
        gameTime = adjustAMPM(gameTime);
        gameDate = gameDate.substring(0,10);
        String gameDateTime = gameDate + " " + gameTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(gameDateTime);
        long gameTimeInMillis = date.getTime();
        long twoHourNotif = gameTimeInMillis - TWO_HOURS_IN_MILLIS;


        // Creates the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(source, CHANNEL_ID)
                .setSmallIcon(R.drawable.viking_logo)
                .setContentTitle(notifTitle)
                .setContentText(notifContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setWhen(twoHourNotif);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(source);
    }

    private static String adjustAMPM(String gameTime) {
        if (gameTime.equals("TBA")) {
            gameTime = "12:00 a.m.";
        }
        if (gameTime.equals("12:00 p.m.")){
            gameTime = "12:00";
        } else if (gameTime.contains("p.m.")) {
            gameTime = ""+Integer.parseInt(gameTime.substring(0,1)) + 12 +":00";
        } else { //gameTime.contains("p.m.")
            gameTime = gameTime.substring(0,gameTime.length()-5);
        }
            return gameTime;
    }

    private static PendingIntent sendUserToOnClick(Context source, Class target) {
        Intent intent = new Intent(source, target);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(source, 0, intent, 0);
        return pendingIntent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
