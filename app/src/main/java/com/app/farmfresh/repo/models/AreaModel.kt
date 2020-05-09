package com.app.farmfresh.repo.models

data class AreaModel(var name:String,
                     var minimumBill : Float,
                     var deliveryCharge : Float,
                     var manager : String = "")