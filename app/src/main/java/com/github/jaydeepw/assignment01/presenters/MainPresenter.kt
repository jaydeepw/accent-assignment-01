package com.github.jaydeepw.assignment01.presenters

import android.util.Log
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.db.AlbumRepository
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback
import com.github.jaydeepw.assignment01.models.datasource.network.MainNetworkModel
import org.greenrobot.eventbus.EventBus



class MainPresenter(_view: MainContractInterface.View?,
                    _albumRepository: AlbumRepository) :
        BasePresenter(), MainContractInterface.Presenter {

    var view = _view
    var albumRepository = _albumRepository

    var mainModel : MainNetworkModel = MainNetworkModel()

    override fun onGetData() {

        albumRepository.getAll(object: AlbumsCallback {
            override fun onSuccess(list: MutableList<Album>) {
                Log.d("MainPresenter", "DB.list.size ${list.size}")
                view?.showData(list as ArrayList<Album>)
            }

            override fun onFailure(messageResId: Int) {
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

            override fun onFailure(message: Int) {
                view?.showError(message)
            }
        })
    }
}