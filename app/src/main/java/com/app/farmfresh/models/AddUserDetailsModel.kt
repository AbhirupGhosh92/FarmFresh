package com.app.farmfresh.models

import com.app.farmfresh.BuildConfig

data class AddUserDetailsModel(
                            var uid : String,
                            var email : String,
                            var mobile : String,
                            var name : String,
                            var address : AddressModel,
                            var role : String = BuildConfig.FLAVOR

)