package com.app.farmfresh.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.farmfresh.models.UserModel
import com.app.farmfresh.repo.Repository
import com.app.farmfresh.repo.models.ResponseModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject

class AuthViewModel : ViewModel() {

    fun checkAccess(id : String) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository .checkAccess(id)
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