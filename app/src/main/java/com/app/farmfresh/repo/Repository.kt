package com.app.farmfresh.repo

import com.app.farmfresh.models.UserModel
import com.app.farmfresh.network.ApiModule
import com.app.farmfresh.network.NetworkInterface
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.ResponseModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.SingleObserver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository  {

    
    private var networkClient: NetworkInterface = ApiModule.networkInterface
    
    private val defaultResponseModel = ResponseModel(504,"GATEWAY TIMEOUT","{}")

    
    fun heartBeat() : Flowable<ResponseModel?>?
    {
        return networkClient.heartBeat()?.defaultIfEmpty(defaultResponseModel)
    }

    
    fun addArea(areaModel: AreaModel) : Flowable<ResponseModel?>?
    {
        return networkClient.addArea(areaModel)?.defaultIfEmpty(defaultResponseModel)
    }

    
    fun editArea(areaModel: AreaModel) : Flowable<ResponseModel?>?
    {
        return networkClient.editArea(areaModel)?.defaultIfEmpty(defaultResponseModel)
    }

    
    fun deleteArea(areaModel: AreaModel) : Flowable<ResponseModel?>?
    {
        return networkClient.deleteArea(areaModel)?.defaultIfEmpty(defaultResponseModel)
    }

    
    fun checkAccess(userId : String) : Flowable<ResponseModel?>?
    {
        return networkClient.checkUser(userId)?.defaultIfEmpty(defaultResponseModel)
    }

    
    fun createUser(user : UserModel) : Flowable<ResponseModel?>?
    {
        return networkClient.createUser(user)?.defaultIfEmpty(defaultResponseModel)
    }

}