package com.github.jaydeepw.assignment01.models.datasource.network

import com.github.jaydeepw.assignment01.Utils
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback
import com.github.jaydeepw.assignment01.models.datasource.network.client.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainNetworkModel: MainContractInterface.Model {

    @Inject
    lateinit var apiClient: ApiInterface

    override fun getData(callback: AlbumsCallback) {

        val list = apiClient.getAlbums()
        list.enqueue(object : Callback<MutableList<Album>> {

            override fun onResponse(call: Call<MutableList<Album>>, response: Response<MutableList<Album>>) {
                if (response.isSuccessful) {
                    var body = response.body()
                    if (body == null) {
                        body = mutableListOf()
                    }

                    callback.onSuccess(body)
                } else {
                    callback.onNotSuccess(Utils.Companion.parseNetworkCode(response.code()))
                }
            }

            override fun onFailure(call: Call<MutableList<Album>>, t: Throwable) {
                callback.onFailure(t.message ?: t.localizedMessage)
            }
        })
    }
}