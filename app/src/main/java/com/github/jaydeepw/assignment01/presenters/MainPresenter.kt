package com.github.jaydeepw.assignment01.presenters

import android.util.Log
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.db.AlbumRepository
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback
import com.github.jaydeepw.assignment01.models.datasource.network.MainNetworkModel
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class MainPresenter(
    var view: MainContractInterface.View?,
    var albumRepository: AlbumRepository): BasePresenter, MainContractInterface.Presenter {

    @Inject
    lateinit var mainModel: MainNetworkModel

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

        mainModel.getData(object : AlbumsCallback {
            override fun onSuccess(list: MutableList<Album>) {
                Log.d("MainPresenter", "network.list.size ${list.size}")
                albumRepository.insertAll(list)

                // notify the subscriber about this event.
                // in our case, it will be the UI.
                EventBus.getDefault().post(list)
            }

            override fun onFailure(message: String) {
                view?.hideProgress()
            }

            override fun onNotSuccess(messageResId: Int) {
                view?.hideProgress()
                view?.showError(messageResId)
            }
        })
    }
}