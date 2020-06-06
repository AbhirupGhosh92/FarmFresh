package com.app.farmfresh.models

import com.app.farmfresh.BuildConfig

data class UserDetailsModel(
                            var uid : String,
                            var email : String,
                            var phoneNumber : String,
                            var name : String,
                            var address : AddressModel,
                            var role : String = BuildConfig.FLAVOR

)