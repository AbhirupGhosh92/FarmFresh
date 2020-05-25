package com.app.farmfresh.repo

import android.util.Log
import com.app.farmfresh.network.NetworkClient
import com.app.farmfresh.network.NetworkInterface
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.ResponseModel
import dagger.Module
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.SingleObserver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@Module
class Repository  {


    @Inject
    lateinit var networkClient: NetworkInterface

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

}