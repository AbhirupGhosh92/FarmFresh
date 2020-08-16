package com.app.farmfresh.viewmodels.managers

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.farmfresh.models.UserModel
import com.app.farmfresh.repo.Repository
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ManagerHomeViewModel : ViewModel() {

    fun getDeliveryBoyList() : MutableLiveData<MutableList<UserModel>>
    {
        var tempLiveData = MutableLiveData<MutableList<UserModel>>()
        Repository.getDeliveryBoyList()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                var temp = mutableListOf<UserModel>()
                it.forEach {
                    temp.add(Gson().fromJson(it.value.toString(),UserModel::class.java))
                }
                tempLiveData.value = temp
            },{
                it.printStackTrace()
            })

        return tempLiveData
    }

}
