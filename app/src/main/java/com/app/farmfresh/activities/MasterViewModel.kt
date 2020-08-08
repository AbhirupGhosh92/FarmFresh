package com.app.farmfresh.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import com.app.farmfresh.constants.ChannelID
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.constants.NotificationConstants
import com.app.farmfresh.network.ApiModule
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MasterViewModel : ViewModel() {

    fun init(context: Context)
    {
        getFirebaseToken()
        ChannelID.values().forEach {
            createNotificationChannel(context,it)
        }
    }

    private fun getFirebaseToken()
    {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                else
                {
                    Log.d("FCM", "token:${task.result?.token.toString()}")
                    ApiModule.fcmToken = task.result?.token.toString()
                }

            })
    }

    private fun createNotificationChannel(context: Context,channelID: ChannelID)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = NotificationConstants.getName(channelID)
            val descriptionText = NotificationConstants.getDescription(channelID)
            val importance = NotificationConstants.getImportance(channelID)
            val channel = NotificationChannel(channelID.name, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}