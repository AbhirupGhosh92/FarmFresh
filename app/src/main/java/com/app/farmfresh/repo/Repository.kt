package com.app.farmfresh.repo

import com.app.farmfresh.models.MobileNumberModel
import com.app.farmfresh.models.UserModel
import com.app.farmfresh.network.ApiModule
import com.app.farmfresh.network.NetworkInterface
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.CheckAccessData
import com.app.farmfresh.repo.models.CheckAccessResponseModel
import com.app.farmfresh.repo.models.ResponseModel
import io.reactivex.rxjava3.core.Flowable

object Repository  {

    
    private var networkClient: NetworkInterface = ApiModule.networkInterface
    
    private val defaultResponseModel = ResponseModel(504,"GATEWAY TIMEOUT","{}")

    private inline fun <reified T> ResponseModel.clone() : Any
    {
        return when(T::class) {
            CheckAccessResponseModel::class -> {
                CheckAccessResponseModel(CheckAccessData(), this.responseMessage,
                    this.status)
            }
            else -> {
                defaultResponseModel
            }
        }
    }



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

    
    fun checkAccess(userId : String) : Flowable<CheckAccessResponseModel?>?
    {
        return networkClient.checkAccess(userId)?.defaultIfEmpty(
            defaultResponseModel.clone<CheckAccessResponseModel>() as CheckAccessResponseModel
        )
    }

    
    fun createUser(user : UserModel) : Flowable<ResponseModel?>?
    {
        return networkClient.createUser(user)?.defaultIfEmpty(defaultResponseModel)
    }

    fun addMobileNumber(id: String,mobileNumberModel: MobileNumberModel) : Flowable<ResponseModel?>?
    {
        return networkClient.addMobileNumber(id,mobileNumberModel)?.defaultIfEmpty(
            defaultResponseModel)
    }

}