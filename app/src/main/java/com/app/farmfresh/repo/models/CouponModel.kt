package com.app.farmfresh.repo.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CouponModel(
    @SerializedName("code") var code : String,
    @SerializedName("amount") var amount : Float,
    @SerializedName("desc") var desc : String
) : Serializable