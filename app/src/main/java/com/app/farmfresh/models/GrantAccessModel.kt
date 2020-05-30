package com.app.farmfresh.models

import com.app.farmfresh.BuildConfig
import com.google.gson.annotations.SerializedName

data class GrantAccessModel(
    @SerializedName("email") var email: String,
    @SerializedName("role") var role : String = BuildConfig.FLAVOR

)