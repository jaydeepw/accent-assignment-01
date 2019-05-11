package com.github.jaydeepw.assignment01.models.datasource.network.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        fun getService() : ApiInterface {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create<ApiInterface>(ApiInterface::class.java)
        }
    }

}