package com.app.farmfresh.repo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(@SerializedName("status")
                         @Expose var status : Int, @SerializedName("responseMessage")
                        @Expose var responseMessage : String, @SerializedName("status")
                        @Expose var data : String)