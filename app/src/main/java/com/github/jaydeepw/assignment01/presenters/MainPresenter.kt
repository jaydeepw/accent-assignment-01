package com.github.jaydeepw.assignment01.presenters

import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.models.datasource.MainDataSource
import com.github.jaydeepw.assignment01.models.datasource.mock.MainMockModel

class MainPresenter(_view: MainContractInterface.View?) : BasePresenter(), MainContractInterface.Presenter {

    var view = _view
    val mainModel : MainDataSource = MainMockModel()

    override fun onData() {
        view?.showData(mainModel.getData())
    }
}