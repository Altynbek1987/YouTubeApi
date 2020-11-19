package com.example.youtubeapi.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://www.googleapis.com/"
class RetrofitClient{

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient:OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(40,TimeUnit.SECONDS)
            .readTimeout(40,TimeUnit.SECONDS)
            .writeTimeout(40,TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
            .build()

    private val retrofit: Retrofit =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    fun instanceRetrofit(): YoutubeApi {
        return retrofit.create(YoutubeApi::class.java)

    }

//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .client(createHttpClient())
//        .baseUrl(BASE_URL)
//        .build()

//    fun createHttpClient(): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder()
//            .readTimeout(20, TimeUnit.SECONDS)
//            .writeTimeout(20, TimeUnit.SECONDS)
//            .addInterceptor(interceptor)
//            .addInterceptor { chain ->
//                val builder = chain.request().newBuilder()
//                return@addInterceptor chain.proceed(builder.build())
//            }
//        return client.build()
//    }

}