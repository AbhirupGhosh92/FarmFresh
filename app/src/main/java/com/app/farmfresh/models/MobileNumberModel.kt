package com.app.farmfresh.models

import com.app.farmfresh.BuildConfig
import com.google.gson.annotations.SerializedName

data class MobileNumberModel(
    @SerializedName("id") var id : String,
    @SerializedName("mobile") var mobile : String,
    @SerializedName("role") var role : String = BuildConfig.FLAVOR
    )