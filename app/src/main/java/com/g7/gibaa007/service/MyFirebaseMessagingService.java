//package com.g7.gibaa007.service;
//
//import android.annotation.SuppressLint;
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.BitmapFactory;
//import android.media.Ringtone;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.support.v4.app.NotificationCompat;
//import android.util.Log;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//import com.pro.quickeeme.R;
//import com.pro.quickeeme.activity.ProfileViewActivity;
//import com.pro.quickeeme.activity.QuickeeDetailActivity;
//import com.pro.quickeeme.activity.SplashActivity;
//
//import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
//import org.apache.commons.lang3.text.translate.EntityArrays;
//import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;
//import org.apache.commons.lang3.text.translate.LookupTranslator;
//
//
///**
// * Created by newagesmb on 20/2/17.
// */
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    private static final String TAG = "MyFirebaseMsgService";
//
//    /**
//     * Called when message is received.
//     *
//     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
//     */
//    // [START receive_message]
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.e("G7", remoteMessage.getData().toString());
////        Toast.makeText(this, remoteMessage.getData().toString(), Toast.LENGTH_SHORT).show();
//        // [START_EXCLUDE]
//        // There are two types of messages data messages and notification messages. Data messages are handled
//        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
//        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
//        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
//        // When the user taps on the notification they are returned to the app. Messages containing both notification
//        // and data payloads are treated as notification messages. The Firebase console always sends notification
//        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
//        // [END_EXCLUDE]
//        try {
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//            r.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // TODO(developer): Handle FCM messages here.
//        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getData() != null) {
//            sendNotification(remoteMessage.getData().get("type"), remoteMessage.getNotification().getBody(), remoteMessage.getData().get("title"), remoteMessage.getData().get("quickee_id"), remoteMessage.getData().get("user_id"));
//        }
//    }
//
//    /**
//     * Create and show a simple notification containing the received FCM message.\
//     */
//
//    private void sendNotification(String tag, String content, String title, String quickee_id, String user_id) {
//
//        Intent intent;
//        if (tag.equals("post_quickee") || tag.equals("favorites") || tag.equals("repost") || tag.equals("comment")) {
//            intent = new Intent(this, QuickeeDetailActivity.class).putExtra("quickeeId", Integer.parseInt(quickee_id));
//        } else if (tag.equals("follow"))
//            intent = new Intent(this, ProfileViewActivity.class).putExtra("id", Integer.parseInt(user_id));
//        else intent = new Intent(this, SplashActivity.class);
////        else if (tag.equals("friend_request") || tag.equals("near_friend")) {
////            intent = new Intent(this, ProfileViewActivity.class);
////            intent.putExtra("userId", id);
////        } else if (tag.equals("new_question") || tag.equals("question_reply")) {
////            intent = new Intent(this, QuestionDetailsActivity.class);
////            intent.putExtra("questId", id);
////            intent.putExtra("title", "Question");
////        } else if (tag.equals("near_exclusive")) {
////            intent = new Intent(this, ExclusiveDetailActivity.class);
////            intent.putExtra("exclusiveId", Integer.parseInt(id));
////        }
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        int icon = R.drawable.logo_img;
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
//                intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
//                this, "CHANNEL_ID");
//        @SuppressLint("ResourceAsColor") Notification notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
//                .setAutoCancel(true)
//                .setContentTitle(unicodeConversion(title))
//                .setContentText(unicodeConversion(content))
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
//                .setContentIntent(resultPendingIntent)
//                .setColor(R.color.colorPrimary)
//                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).build();
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notification);
//    }
//
//
//    public String unicodeConversion(String content) {
//
//        final CharSequenceTranslator ESCAPE_JAVA =
//                new LookupTranslator(
//                        new String[][]{
//                                {"\"", "\""},
//                                {"\\", "\\\\"},
//                        }).with(
//                        new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE())
//                ).with(JavaUnicodeEscaper.outsideOf(32, 0x7f));
//
//        return ESCAPE_JAVA.translate(content);
//
//    }
//}