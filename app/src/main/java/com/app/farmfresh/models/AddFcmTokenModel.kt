package com.app.farmfresh.models

import com.app.farmfresh.BuildConfig
import com.app.farmfresh.network.ApiModule
import com.google.gson.annotations.SerializedName

data class AddFcmTokenModel(
    @SerializedName("id") var id: String,
    @SerializedName("role") var role : String = BuildConfig.FLAVOR,
    @SerializedName("token") var token : String = ApiModule.fcmToken
)