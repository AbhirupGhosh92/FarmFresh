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
import com.app.farmfresh.models.AddFcmTokenModel
import com.app.farmfresh.network.ApiModule
import com.app.farmfresh.repo.Repository
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId

class MasterViewModel : ViewModel() {

    private lateinit var context: Context
    fun init(context: Context)
    {
        this.context = context
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
                    if(context.getSharedPreferences("config",Context.MODE_PRIVATE).getBoolean("token_refresh",true) && FirebaseAuth.getInstance().uid.isNullOrEmpty().not())
                    {
                        context.getSharedPreferences("config",Context.MODE_PRIVATE).edit().apply {
                            mapFcmTokenOnServer()
                            putBoolean("token_refresh",false)
                        }.apply()

                    }

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

    private fun mapFcmTokenOnServer()
    {
        Repository.addFcmToken(
            AddFcmTokenModel(FirebaseAuth.getInstance().uid.toString())
        )
    }

}