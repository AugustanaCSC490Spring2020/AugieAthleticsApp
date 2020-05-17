package edu.augustanacsc490spring2020.augieathletics;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import edu.augustanacsc490spring2020.augieathletics.UpcomingGms.UpcomingGms;

import static edu.augustanacsc490spring2020.augieathletics.MainActivity.CHANNEL_ID;

// Class created to handle creating notifications for upcoming games
public class NotificationCreator {

    //Majority of code pulled from https://developer.android.com/training/notify-user/build-notification
    public static void createNotif(Context source, String notifTitle, String notifContent) {
        // Adds functionality when notification is Clicked
        PendingIntent pendingIntent = sendUserToOnClick(source, UpcomingGms.class);

        // Creates the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(source, CHANNEL_ID)
                .setSmallIcon(R.drawable.viking_logo)
                .setContentTitle(notifTitle)
                .setContentText(notifContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

    private static PendingIntent sendUserToOnClick(Context source, Class target) {
        Intent intent = new Intent(source, target);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(source, 0, intent, 0);
        return pendingIntent;
    }
}
