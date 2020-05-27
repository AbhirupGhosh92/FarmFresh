package com.app.farmfresh.repo.models

import com.google.gson.annotations.SerializedName

data class CheckAccessData(

    @field:SerializedName("accessGranted")
    val accessGranted: Boolean = false,
    @field:SerializedName("mobileAdded")
    val mobileAdded : Boolean = false,
    @field:SerializedName("detailsAdded")
    val detailsAdded : Boolean = false
)