package com.github.jaydeepw.assignment01.models.datasource.network

import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback
import com.github.jaydeepw.assignment01.models.datasource.network.client.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainNetworkModel: MainContractInterface.Model {

    override fun getData(callback: AlbumsCallback) {
        // todo: network call here.

        val list = ApiClient.getService().getAlbums()
        list.enqueue(object : Callback<MutableList<Album>> {

            override fun onResponse(call: Call<MutableList<Album>>, response: Response<MutableList<Album>>) {
                if (response.isSuccessful) {
                    var body = response.body()
                    if (body == null) {
                        body = mutableListOf()
                    }

                    callback.onSuccess(body)
                } else {
                    // TODO: Parse message for unsuccessful response
                    callback.onFailure("Error in API call")
                }
            }

            override fun onFailure(call: Call<MutableList<Album>>, t: Throwable) {
                // TODO: Parse message for unsuccessful response
                callback.onFailure("Error in API call")
            }
        })
    }
}