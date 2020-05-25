package com.app.farmfresh.network

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.internal.schedulers.RxThreadFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NetworkClient @Inject constructor(private val interceptor: HttpLoggingInterceptor,private val okHttpClient: OkHttpClient
                                        ,private val retrofit: Retrofit,private val networkInterface: NetworkInterface) {


    @Singleton
    @Provides
    fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun getClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://us-central1-farmerfresh-1c6f2.cloudfunctions.net/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getNetworkInterface(): NetworkInterface {
        return retrofit.create(NetworkInterface::class.java)
    }

}