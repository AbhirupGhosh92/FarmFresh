package com.app.farmfresh.repo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(@SerializedName("status")
                        var status : Int,
                         @SerializedName("responseMessage")
                        var responseMessage : String,
                         @SerializedName("data")
                        var data : String)