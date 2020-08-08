package com.app.farmfresh.constants

import android.app.Notification
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

object NotificationConstants {

    fun getName(enum: Enum<ChannelID>) : String
    {
       return enum.name
    }

    fun getDescription(enum: Enum<ChannelID>) : String
    {
        var desc = ""

        when(enum)
        {
            ChannelID.USER_CREATION -> {
                desc = "A new user has been created or access has been granted"
            }

            ChannelID.USER_NOTIFICATION -> {
                desc = "A notification has been sent to the user"
            }
        }
        return desc
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getImportance(enum: Enum<ChannelID>) : Int
    {
        var importance = NotificationManager.IMPORTANCE_DEFAULT
       when(enum)
       {
           ChannelID.USER_CREATION -> {
               importance = NotificationManager.IMPORTANCE_HIGH
           }

           ChannelID.USER_NOTIFICATION -> {
               importance = NotificationManager.IMPORTANCE_DEFAULT
           }
       }

        return importance
    }
}