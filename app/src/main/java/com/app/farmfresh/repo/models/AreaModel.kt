package com.app.farmfresh.repo.models

import java.io.Serializable

data class AreaModel(var name:String,
                     var minimumBill : Float,
                     var deliveryCharge : Float,
                     var manager : String = ""):Serializable