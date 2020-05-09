package com.app.farmfresh.repo

import android.util.Log
import com.app.farmfresh.network.NetworkClient
import com.app.farmfresh.repo.models.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    fun heartBeat()
    {
        NetworkClient.service.heartBeat()?.enqueue(object : Callback<ResponseModel?>{
            override fun onFailure(call: Call<ResponseModel?>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ResponseModel?>,
                response: Response<ResponseModel?>
            ) {
                if(response.body()!=null && response.isSuccessful)
                {

                }
            }

        })
    }

}