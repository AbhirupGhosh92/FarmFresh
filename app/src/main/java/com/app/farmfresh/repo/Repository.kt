package com.app.farmfresh.repo

import com.app.farmfresh.models.*
import com.app.farmfresh.network.ApiModule
import com.app.farmfresh.network.NetworkInterface
import com.app.farmfresh.repo.models.*
import com.google.firebase.database.*
import com.google.gson.Gson
import io.reactivex.rxjava3.core.*
import org.intellij.lang.annotations.Flow
import org.reactivestreams.Subscriber

object Repository  {


    private var db : FirebaseDatabase = FirebaseDatabase.getInstance().also {
        it.setLogLevel(Logger.Level.DEBUG)
        it.setPersistenceEnabled(true)
    }



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

    fun getAreaList() : Flowable<Iterable<DataSnapshot>>
    {
        return Flowable.create({

            db.getReference("area/").addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {

                    if(p0.value!=null) {
                       it.onNext(p0.children)
                    }
                }

            })

        },BackpressureStrategy.BUFFER)
    }

    fun getCouponList() : Flowable<Iterable<DataSnapshot>>
    {
        return Flowable.create({

            db.getReference("coupon/").addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {

                    if(p0.value!=null) {
                        it.onNext(p0.children)
                    }
                }

            })

        },BackpressureStrategy.BUFFER)
    }


    fun addCoupon(couponModel: CouponModel) : Flowable<ResponseModel?>?
    {
        return networkClient.addCoupon(couponModel)?.defaultIfEmpty(defaultResponseModel)
    }


    fun editCoupon(couponModel: CouponModel) : Flowable<ResponseModel?>?
    {
        return networkClient.addCoupon(couponModel)?.defaultIfEmpty(defaultResponseModel)
    }


    fun deleteCoupon(couponModel: CouponModel) : Flowable<ResponseModel?>?
    {
        return networkClient.addCoupon(couponModel)?.defaultIfEmpty(defaultResponseModel)
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

    
    fun checkAccess(checkAccessModel: CheckAccessModel) : Flowable<CheckAccessResponseModel?>?
    {
        return networkClient.checkAccess(checkAccessModel)?.defaultIfEmpty(
            defaultResponseModel.clone<CheckAccessResponseModel>() as CheckAccessResponseModel
        )
    }

    
    fun createUser(user : UserModel) : Flowable<ResponseModel?>?
    {
        return networkClient.createUser(user)?.defaultIfEmpty(defaultResponseModel)
    }

    fun addMobileNumber(mobileNumberModel: MobileNumberModel) : Flowable<ResponseModel?>?
    {
        return networkClient.addMobileNumber(mobileNumberModel)?.defaultIfEmpty(
            defaultResponseModel)
    }

    fun grantAccess(grantAccessModel: GrantAccessModel) : Flowable<ResponseModel?>?
    {
        return networkClient.grantAccess(grantAccessModel)?.defaultIfEmpty(
            defaultResponseModel)
    }

    fun addUserDetails(grantAccessModel: UserDetailsModel) : Flowable<ResponseModel?>?
    {
        return networkClient.addUserDetails(grantAccessModel)?.defaultIfEmpty(
            defaultResponseModel)
    }



}