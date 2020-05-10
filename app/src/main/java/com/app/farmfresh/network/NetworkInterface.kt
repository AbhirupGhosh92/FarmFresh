package com.app.farmfresh.network

import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.ResponseModel
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface NetworkInterface {

    @GET("heartbeat/")
    fun heartBeat() : Flowable<ResponseModel?>?

    @POST("addArea/")
    fun addArea(@Body areaModel:AreaModel) : Flowable<ResponseModel?>?

    @POST("addArea/")
    fun createDeliveryBoy(@Body areaModel:AreaModel) : Flowable<ResponseModel?>?
}

