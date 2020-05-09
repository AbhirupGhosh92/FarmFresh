package com.app.farmfresh.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkClient {

    companion object{
       private val okHttpClient = OkHttpClient().newBuilder()
           .addInterceptor(LoggingInterceptor())
            .build()

        private val retrofitClient = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://us-central1-farmerfresh-1c6f2.cloudfunctions.net/")
            .build()

        var service: NetworkInterface = retrofitClient.create(NetworkInterface::class.java)
    }
}