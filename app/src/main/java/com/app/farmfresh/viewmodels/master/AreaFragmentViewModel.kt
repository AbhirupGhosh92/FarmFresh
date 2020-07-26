package com.app.farmfresh.viewmodels.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.farmfresh.repo.Repository
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.ResponseModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class AreaFragmentViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun addArea(areaModel: AreaModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository.addArea(areaModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
        it.printStackTrace()
    })

        return liveData
    }


    fun deleteArea(areaModel: AreaModel) : LiveData<ResponseModel>
    {

        var liveData = MutableLiveData<ResponseModel>()

        Repository.deleteArea(areaModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                liveData.value = it
            },{
                it.printStackTrace()
            })

        return liveData
    }

    fun editArea(areaModel: AreaModel) : LiveData<ResponseModel>
    {
        var liveData = MutableLiveData<ResponseModel>()

        Repository.editArea(areaModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({resp ->
                liveData.value = resp
            },{
                it.printStackTrace()
            })

        return liveData
    }

    fun getAreaList() : LiveData<List<AreaModel>>
    {
        var data = MutableLiveData<List<AreaModel>>()
        var temp = mutableListOf<AreaModel>()

        Repository.getAreaList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({

                temp.clear()

                it.forEach {
                    var tempObj = it.value as HashMap<String,String?>
                    if(tempObj!=null)
                        temp.add(Gson().fromJson(Gson().toJsonTree(it.value),AreaModel::class.java))
                }

                data.value = temp

            },{
                it.printStackTrace()
            })

        return data
    }

}
