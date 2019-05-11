package com.github.jaydeepw.assignment01.presenters

import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback
import com.github.jaydeepw.assignment01.models.datasource.MainDataSource
import com.github.jaydeepw.assignment01.models.datasource.network.MainNetworkModel

class MainPresenter(_view: MainContractInterface.View?) : BasePresenter(), MainContractInterface.Presenter {

    var view = _view
    private val mainModel : MainDataSource = MainNetworkModel()

    override fun onData() {
        mainModel.getData(object : AlbumsCallback {
            override fun onFailure(message: String) {
                view?.showError(message)
            }

            override fun onSuccess(list: MutableList<Album>) {
                view?.showData(list)
            }

        })
    }
}