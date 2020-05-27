package com.app.farmfresh.models

import com.google.gson.annotations.SerializedName

data class UserModel(

	@field:SerializedName("uid")
	val uid:String?=null,

	@field:SerializedName("emailVerified")
	val emailVerified: Boolean? = null,

	@field:SerializedName("photoURL")
	val photoURL: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("disabled")
	val disabled: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null
)
