package com.app.farmfresh.cloudmessaging

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationBuilderWithBuilderAccessor
import com.app.farmfresh.constants.ChannelID
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.constants.NotificationConstants
import com.app.farmfresh.network.ApiModule
import com.google.android.gms.common.util.SharedPreferencesUtils
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CloudMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String?) {
        Log.d("FCM" , "Token:${p0}")
        ApiModule.fcmToken = p0.toString()
    }



    override fun onMessageReceived(p0: RemoteMessage?) {

        Log.d("FCM" , "From:${p0?.from}")

        if(p0?.data?.size!! > 0)
        {
            Log.d("FCM", "Message data payload: " + p0.data)
        }

        if (p0.notification != null) {
           NotificationHandler.showNotification(baseContext,ChannelID.USER_CREATION.name,p0)
        }

    }
}
