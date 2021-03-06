package com.app.farmfresh.network

import com.app.farmfresh.models.*
import com.app.farmfresh.repo.models.*
import io.reactivex.rxjava3.core.Flowable
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface NetworkInterface {


    @GET("heartbeat/")
    fun heartBeat() : Flowable<ResponseModel?>?

    @POST("addArea/")
    fun addArea(@Body areaModel:AreaModel) : Flowable<ResponseModel?>?

    @POST("editArea/")
    fun editArea(@Body areaModel:AreaModel) : Flowable<ResponseModel?>?

    @POST("deleteArea/")
    fun deleteArea(@Body areaModel:AreaModel) : Flowable<ResponseModel?>?

    @POST("addCoupon/")
    fun addCoupon(@Body couponModel: CouponModel) : Flowable<ResponseModel?>?

    @POST("editCoupon/")
    fun editCoupon(@Body couponModel: CouponModel) : Flowable<ResponseModel?>?

    @POST("deleteCoupon/")
    fun deleteCoupon(@Body couponModel: CouponModel) : Flowable<ResponseModel?>?

    @POST("createDeliveryBoy/")
    fun createDeliveryBoy(@Body deliveryBoyModel: DeliveryBoyModel) : Flowable<ResponseModel?>?

    @POST("checkAccess/")
    fun checkAccess(@Body checkAccessModel: CheckAccessModel) : Flowable<CheckAccessResponseModel?>?

    @POST("createUser/")
    fun createUser(@Body userModel : UserModel) : Flowable<ResponseModel?>?

    @POST("addMobileNumber/")
    fun addMobileNumber( @Body mobileNumberModel: MobileNumberModel) : Flowable<ResponseModel?>?

    @POST("grantAccess/")
    fun grantAccess( @Body grantAccessModel: GrantAccessModel) : Flowable<ResponseModel?>?

    @POST("addUserDetails/")
    fun addUserDetails( @Body grantAccessModel: AddUserDetailsModel) : Flowable<ResponseModel?>?

    @POST("addFcmToken/")
    fun addFcmToken(@Body addFcmTokenModel: AddFcmTokenModel) : Flowable<ResponseModel?>?
}

