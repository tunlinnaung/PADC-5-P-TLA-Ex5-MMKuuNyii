package xyz.tunlinaung.mmkuunyii.fcm

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import xyz.tunlinaung.mmkuunyii.KuuNyiiApp
import xyz.tunlinaung.mmkuunyii.utils.NotificationUtils

class AppFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // Handle data payload of FCM messages.
        Log.d(KuuNyiiApp.TAG, "FCM Message : " + remoteMessage!!.messageId!!)
        Log.d(KuuNyiiApp.TAG, "FCM Notification Message: " + remoteMessage.notification!!)
        Log.d(KuuNyiiApp.TAG, "FCM Data Message: " + remoteMessage.data)

        val remoteMsgData = remoteMessage.data
        val message = remoteMsgData[NotificationUtils.KEY_MESSAGE]

        NotificationUtils.notifyCustomMsg(applicationContext, message?: "Dummy")
    }
}