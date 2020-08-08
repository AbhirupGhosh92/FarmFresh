package com.app.farmfresh.cloudmessaging

import android.app.Notification
import android.content.Context
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.app.farmfresh.R
import com.app.farmfresh.constants.ChannelID
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.constants.NotificationConstants
import com.google.firebase.messaging.RemoteMessage
import kotlinx.android.synthetic.main.fragment_add_edit_coupon.view.*

object NotificationHandler {

    fun showNotification(context: Context,channelId : String,remoteMessage: RemoteMessage)
    {

        var payload = if(remoteMessage.data.isNullOrEmpty().not())
            remoteMessage.data
        else
            null

        var builder : NotificationCompat.Builder = when(channelId) {
            ChannelID.USER_CREATION.name -> {
                NotificationCompat.Builder(context,channelId)
                    .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                    .setContentTitle(remoteMessage.notification?.title)
            }
            else -> {
                NotificationCompat.Builder(context,channelId)
                    .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                    .setContentTitle(remoteMessage.notification?.title)
            }
        }

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(SystemClock.currentThreadTimeMillis().toInt(), builder.build())
        }
    }

}