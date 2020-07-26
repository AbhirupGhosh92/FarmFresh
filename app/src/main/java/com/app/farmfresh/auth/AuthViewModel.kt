package com.app.farmfresh.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.farmfresh.models.CheckAccessModel
import com.app.farmfresh.models.MobileNumberModel
import com.app.farmfresh.models.AddUserDetailsModel
import com.app.farmfresh.models.UserModel
import com.app.farmfresh.repo.Repository
import com.app.farmfresh.repo.models.CheckAccessResponseModel
import com.app.farmfresh.repo.models.ResponseModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthViewModel : ViewModel() {

    fun checkAccess(checkAccessModel: CheckAccessModel) : LiveData<CheckAccessResponseModel>
    {
        var liveData = MutableLiveData<CheckAccessResponseModel>()

        Repository .checkAccess(checkAccessModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
                it.printStackTrace()
            })

        return liveData
    }

    fun addMonbileNumber(mobileNumberModel: MobileNumberModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository .addMobileNumber(mobileNumberModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
                it.printStackTrace()
            })

        return liveData
    }

    fun addUserDetails(userDetailsModel: AddUserDetailsModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository.addUserDetails(userDetailsModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
                it.printStackTrace()
            })

        return liveData

    }

    fun createUser(user : UserModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository.createUser(user)
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