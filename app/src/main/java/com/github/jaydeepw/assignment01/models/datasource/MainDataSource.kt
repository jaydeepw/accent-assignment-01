package com.github.jaydeepw.assignment01.models.datasource

import com.github.jaydeepw.assignment01.models.dataclasses.Album

interface MainDataSource {

    fun getData(callback: AlbumsCallback)
}

interface AlbumsCallback {
    fun onSuccess(list : MutableList<Album>)
    fun onNotSuccess(messageResId: Int)
    fun onFailure(message: String)
}
