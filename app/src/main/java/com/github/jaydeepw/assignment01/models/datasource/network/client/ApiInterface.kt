package com.github.jaydeepw.assignment01.models.datasource.network.client

import com.github.jaydeepw.assignment01.models.dataclasses.Profile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/profile")
    fun getProfile(@Query("email") email: String): Call<Profile>
}