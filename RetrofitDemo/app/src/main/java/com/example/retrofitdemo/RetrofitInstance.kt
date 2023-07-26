package com.example.retrofitdemo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {


    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com"

        private val  interceptor = HttpLoggingInterceptor().apply {
            this.level= HttpLoggingInterceptor.Level.BODY
        }
        private val client =OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor).
            connectTimeout(30,TimeUnit.SECONDS).
            readTimeout(30,TimeUnit.SECONDS).
            writeTimeout(30,TimeUnit.SECONDS)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(client)
                .build()
        }
    }
}