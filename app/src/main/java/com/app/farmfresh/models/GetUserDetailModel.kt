package com.app.farmfresh.models

import com.app.farmfresh.BuildConfig

data class GetUserDetailModel(
    var uid : String,
    var email : String,
    var mobile : String,
    var name : String,
    var address : HashMap<String,AddressModel>,
    var role : String = BuildConfig.FLAVOR
)