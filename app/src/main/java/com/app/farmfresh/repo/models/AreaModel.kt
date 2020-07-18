package com.app.farmfresh.repo.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AreaModel(
    @SerializedName("name")var name:String,
    @SerializedName("minimumBill")var minimumBill : Float,
    @SerializedName("deliveryCharge")var deliveryCharge : Float,
    @SerializedName("manager")var manager : String = "",
    @SerializedName("areaId")var areaId: String = "${name}_${minimumBill.toInt()}_${manager}"):Serializable