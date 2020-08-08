package com.app.farmfresh.network

import com.app.farmfresh.constants.Constants
import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.internal.schedulers.RxThreadFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiModule {


    companion object{

        var fcmToken = ""

        private var interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private var okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        private var retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        var networkInterface = retrofit.create(NetworkInterface::class.java)
    }


}