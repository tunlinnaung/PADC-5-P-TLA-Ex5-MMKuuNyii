package xyz.tunlinaung.mmkuunyii.fcm

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import xyz.tunlinaung.mmkuunyii.KuuNyiiApp

class AppFirebaseInstanceIdService : FirebaseInstanceIdService() {

    /**
     * The Application's current Instance ID token is no longer valid and thus a new one must be requested.
     */
    override fun onTokenRefresh() {
        // If you need to handle the generation of a token, initially or
        // after a refresh this is where you should do that.
        val token = FirebaseInstanceId.getInstance().token
        //TODO syncUserRegistrationId(token);
        Log.d(KuuNyiiApp.TAG, "FCM Token : " + token!!)
    }
}