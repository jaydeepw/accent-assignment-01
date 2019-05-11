package com.github.jaydeepw.assignment01.models.datasource.mock

import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback

class MainMockModel: MainContractInterface.Model {
    override fun getData(callback: AlbumsCallback) {
        val album1 = Album()
        val album2 = Album()

        callback.onSuccess(mutableListOf(album1, album2))
    }
}