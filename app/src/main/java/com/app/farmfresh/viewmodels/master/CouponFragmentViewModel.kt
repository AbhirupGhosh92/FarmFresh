package com.app.farmfresh.viewmodels.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.farmfresh.repo.Repository
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.CouponModel
import com.app.farmfresh.repo.models.ResponseModel
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CouponFragmentViewModel : ViewModel() {

    fun addCoupon(couponModel: CouponModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository.addCoupon(couponModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
                it.printStackTrace()
            })

        return liveData
    }

    fun editCoupon(couponModel: CouponModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository.editCoupon(couponModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
                it.printStackTrace()
            })

        return liveData
    }

    fun getCouponList() : LiveData<List<CouponModel>>
    {
        var data = MutableLiveData<List<CouponModel>>()
        var temp = mutableListOf<CouponModel>()

        Repository.getCouponList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                temp.clear()
                it.forEach {
                    var tempObj = it.value as HashMap<String,String?>
                    if(tempObj!=null)
                        temp.add(Gson().fromJson(Gson().toJsonTree(it.value), CouponModel::class.java))
                }

                data.value = temp

            },{
                it.printStackTrace()
            })

        return data
    }

    fun deleteCoupon(couponModel: CouponModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository.deleteCoupon(couponModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
                it.printStackTrace()
            })

        return liveData
    }
}