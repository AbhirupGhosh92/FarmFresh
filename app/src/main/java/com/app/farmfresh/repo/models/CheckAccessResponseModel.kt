package com.app.farmfresh.repo.models

import com.google.gson.annotations.SerializedName

data class CheckAccessResponseModel(

	@field:SerializedName("data")
	val data: CheckAccessData,

	@field:SerializedName("responseMessage")
	val responseMessage: String,

	@field:SerializedName("status")
	val status: Int
)
