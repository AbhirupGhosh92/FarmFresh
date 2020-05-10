package com.app.farmfresh.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        Log.d("Request","################################")
        Log.d("Url",request.url().toString())
        Log.d("Method",request.method())
        Log.d("Payload",request.body().toString())
        Log.d("","################################")

        var responne =  chain.proceed(request)


        Log.d("Response","################################")
        Log.d("Status",responne.code().toString())
       // Log.d("Payload",responne.body()?.string().toString())
        Log.d("","################################")

        return responne
    }
}