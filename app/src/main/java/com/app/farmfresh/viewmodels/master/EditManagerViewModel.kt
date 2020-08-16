package com.app.farmfresh.viewmodels.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.farmfresh.constants.Constants
import com.app.farmfresh.models.AddressModel
import com.app.farmfresh.models.GrantAccessModel
import com.app.farmfresh.models.AddUserDetailsModel
import com.app.farmfresh.models.GetUserDetailModel
import com.app.farmfresh.repo.Repository
import com.app.farmfresh.repo.models.ResponseModel
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class EditManagerViewModel : ViewModel() {

    fun grantAccess(grantAccessModel: GrantAccessModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository.grantAccess(grantAccessModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
                it.printStackTrace()
            })

        return liveData

    }

    fun getManagerList() : LiveData<List<GetUserDetailModel>>
    {
        var temp = mutableListOf<GetUserDetailModel>()
        var tempAddressList = hashMapOf<String,AddressModel>()
        var liveData = MutableLiveData<List<GetUserDetailModel>>()
        Repository.getManagerList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                temp.clear()
                it.forEach {

                    var tempObj = it.value as HashMap<*, *>
                    var address = tempObj["address"] as HashMap<*,*>
                    tempAddressList.clear()

                    for(key in address.keys)
                    {
                        tempAddressList[key.toString()] = Gson().fromJson(Gson().toJsonTree(address[key]),AddressModel::class.java)
                    }

                    var userDetailsModel = GetUserDetailModel(
                        it.key.toString(), tempObj["email"].toString(),tempObj["mobile"].toString(),
                        tempObj["name"].toString(),tempAddressList,Constants.manager)

                    temp.add(userDetailsModel)
                }

                liveData.value = temp

            },{
                it.printStackTrace()
            })

        return liveData
    }

    fun getDeliveryBoyList() : LiveData<List<GetUserDetailModel>>{

        var temp = mutableListOf<GetUserDetailModel>()
        var tempAddressList = hashMapOf<String, AddressModel>()
        var liveData = MutableLiveData<List<GetUserDetailModel>>()

        Repository.getDeliveryBoyList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({

                temp.clear()
                it.forEach {

                    var tempObj = it.value as HashMap<*, *>
                    var address = tempObj["address"] as HashMap<*, *>
                    tempAddressList.clear()

                    for (key in address.keys) {
                        tempAddressList[key.toString()] = Gson().fromJson(
                            Gson().toJsonTree(address[key]),
                            AddressModel::class.java
                        )
                    }

                    var userDetailsModel = GetUserDetailModel(
                        it.key.toString(),
                        tempObj["email"].toString(),
                        tempObj["mobile"].toString(),
                        tempObj["name"].toString(),
                        tempAddressList,
                        Constants.manager
                    )

                    temp.add(userDetailsModel)
                }

                liveData.value = temp

            }
                , {
                    it.printStackTrace()
                })

        return liveData
    }
}