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
import com.app.farmfresh.models.AddFcmTokenModel
import com.app.farmfresh.network.ApiModule
import com.app.farmfresh.repo.Repository
import com.google.android.gms.common.util.SharedPreferencesUtils
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CloudMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String?) {
        Log.d("FCM" , "Token:${p0}")
        ApiModule.fcmToken = p0.toString()

        if(FirebaseAuth.getInstance().uid.isNullOrEmpty().not())
        {
            Repository.addFcmToken(AddFcmTokenModel(
                FirebaseAuth.getInstance().uid.toString()
            ))?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe ({
                    if(it?.status == 200)
                    {
                        getSharedPreferences("config",Context.MODE_PRIVATE).edit().apply{
                            putBoolean("token_refresh",false)
                        }.apply()
                    }
                },{
                    it.printStackTrace()
                })
        }
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
