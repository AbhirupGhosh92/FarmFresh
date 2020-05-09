package com.app.farmfresh.network

import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.ResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface NetworkInterface {

    @GET("heartbeat/")
    fun heartBeat() : Call<ResponseModel?>?

    @POST("addArea/")
    fun addArea(@Body areaModel:AreaModel) : Call<ResponseModel?>?

    @POST("addArea/")
    fun createDeliveryBoy(@Body areaModel:AreaModel) : Call<ResponseModel?>?
}

