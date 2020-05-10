package com.app.farmfresh.network

import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.internal.schedulers.RxThreadFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkClient {

    companion object{

        private var interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

       private val okHttpClient = OkHttpClient().newBuilder()
           .addInterceptor(interceptor)
            .build()

        private val retrofitClient = Retrofit.Builder()
            .baseUrl("https://us-central1-farmerfresh-1c6f2.cloudfunctions.net/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        var service: NetworkInterface = retrofitClient.create(NetworkInterface::class.java)
    }
}