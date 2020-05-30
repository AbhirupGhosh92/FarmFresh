package com.app.farmfresh.models

import com.app.farmfresh.BuildConfig
import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

data class CheckAccessModel (@SerializedName("id") val id : String,
                             @SerializedName("role") val role : String = BuildConfig.FLAVOR)