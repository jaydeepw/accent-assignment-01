package com.github.jaydeepw.assignment01.presenters

import android.util.Log
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.db.dao.AlbumDao
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback
import com.github.jaydeepw.assignment01.models.datasource.network.MainNetworkModel

class MainPresenter(_view: MainContractInterface.View?, _albumDao: AlbumDao) : BasePresenter(), MainContractInterface.Presenter {

    var view = _view
    var albumDao = _albumDao

    var mainModel : MainNetworkModel = MainNetworkModel()

    override fun onGetData() {
        mainModel.getData(object : AlbumsCallback {
            override fun onSuccess(list: MutableList<Album>) {
                view?.showData(list as ArrayList<Album>)
                albumDao.insertAll(list)
                Log.d("MainPresenter", "albumDao.size ${albumDao.all.size}")
            }

            override fun onFailure(message: String) {
                view?.showError(message)
            }

        })
    }
}