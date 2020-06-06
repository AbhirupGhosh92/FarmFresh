package com.app.farmfresh.models

data class AddressModel(
                    var primary : Boolean,
                        var pincode : String,
                         var street : String,
                         var locality : String,
                         var city : String,
                         var state : String,
                         var country : String)