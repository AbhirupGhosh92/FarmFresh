package com.app.farmfresh.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.models.AddressModel
import com.app.farmfresh.models.GetUserDetailModel
import com.app.farmfresh.repo.Repository
import com.google.firebase.database.core.Repo
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfileViewModel : ViewModel() {

    fun getCurrentProfileData() : LiveData<GetUserDetailModel>
    {
        var temp = mutableListOf<GetUserDetailModel>()
        var tempAddressList = hashMapOf<String, AddressModel>()
        var liveData = MutableLiveData<GetUserDetailModel>()

        Repository.getCurrentUserDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({

                var tempObj = it.value as HashMap<*, *>
                var address = tempObj["address"] as HashMap<*,*>
                tempAddressList.clear()

                for(key in address.keys)
                {
                    tempAddressList[key.toString()] = Gson().fromJson(Gson().toJsonTree(address[key]),AddressModel::class.java)
                }

                var userDetailsModel = GetUserDetailModel(
                    it.key.toString(), tempObj["email"].toString(),tempObj["mobile"].toString(),
                    tempObj["name"].toString(),tempAddressList, Constants.manager)

                liveData.value = userDetailsModel

            }
                ,
                {
                    it.printStackTrace()
                })

        return liveData
    }

}