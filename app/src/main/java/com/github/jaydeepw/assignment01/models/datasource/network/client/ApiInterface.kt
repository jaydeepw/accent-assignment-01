package com.github.jaydeepw.assignment01.models.datasource.network.client

import com.github.jaydeepw.assignment01.models.dataclasses.Album
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/albums")
    fun getAlbums(): Call<MutableList<Album>>
}