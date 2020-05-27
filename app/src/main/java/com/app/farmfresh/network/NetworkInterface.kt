package com.app.farmfresh.network

import android.os.Build
import com.app.farmfresh.BuildConfig
import com.app.farmfresh.models.UserModel
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.DeliveryBoyModel
import com.app.farmfresh.repo.models.ResponseModel
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

    @POST("createDeliveryBoy/")
    fun createDeliveryBoy(@Body deliveryBoyModel: DeliveryBoyModel) : Flowable<ResponseModel?>?

    @GET("checkUser/")
    fun checkUser(@Query("id") id : String,@Query("role") role : String = BuildConfig.FLAVOR) : Flowable<ResponseModel?>?

    @POST("createUser/")
    fun createUser(@Body userModel : UserModel) : Flowable<ResponseModel?>?
}

