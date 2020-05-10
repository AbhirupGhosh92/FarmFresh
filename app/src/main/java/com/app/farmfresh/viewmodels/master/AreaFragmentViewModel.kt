package com.app.farmfresh.viewmodels.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.farmfresh.repo.Repository
import com.app.farmfresh.repo.models.AreaModel
import com.app.farmfresh.repo.models.ResponseModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers


class AreaFragmentViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun getAreaList() : LiveData<List<AreaModel>>?
    {
        return null
    }

    fun addArea(areaModel: AreaModel)
    {

    }

    fun deleteArea(areaModel: AreaModel)
    {

    }

    fun editArea(areaModel: AreaModel)
    {

    }

}
