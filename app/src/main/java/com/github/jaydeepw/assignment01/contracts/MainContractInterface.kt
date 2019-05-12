package com.github.jaydeepw.assignment01.contracts

import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.MainDataSource
import com.github.jaydeepw.assignment01.views.BaseView

interface MainContractInterface {

    interface View: BaseView {
        fun showData(list: ArrayList<Album>?)
        fun showError(messageResId: Int)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onGetData()
    }

    interface Model : MainDataSource
}