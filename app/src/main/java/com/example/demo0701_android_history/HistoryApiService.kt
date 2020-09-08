package com.example.demo0701_android_history

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://www.gproom.tech"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .baseUrl(BASE_URL)
//    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HistoryApiService {
    @GET("/static/mock/android_v.json")
    fun getProperties():
            //Call<String>
            Call<List<AndroidHistoryData>>
}

object HistoryApi {
    val retrofitService : HistoryApiService by lazy {
        retrofit.create(HistoryApiService::class.java) }
}