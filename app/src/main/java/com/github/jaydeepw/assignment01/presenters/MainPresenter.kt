package com.github.jaydeepw.assignment01.presenters

import android.util.Log
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.db.AlbumRepository
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback

class MainPresenter(
    var view: MainContractInterface.View?,
    var albumRepository: AlbumRepository): BasePresenter, MainContractInterface.Presenter {

    override fun onGetData() {

        view?.showProgress()
        albumRepository.getAll(object : AlbumsCallback {
            override fun onNotSuccess(messageResId: Int) {
                view?.hideProgress()
                view?.showError(messageResId)
            }

            override fun onFailure(message: String) {
                view?.hideProgress()
            }

            override fun onSuccess(list: MutableList<Album>) {
                Log.d("MainPresenter", "DB.list.size ${list.size}")
                view?.hideProgress()
                view?.showData(list as ArrayList<Album>)
            }
        })


    }
}