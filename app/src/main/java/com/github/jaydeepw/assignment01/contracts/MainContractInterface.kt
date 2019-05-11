package com.github.jaydeepw.assignment01.contracts

import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.MainDataSource
import com.github.jaydeepw.assignment01.views.BaseView

interface MainContractInterface {

    interface View: BaseView {
        fun showData(list: List<Album>?)
        fun showError(message: String)
    }

    interface Presenter {
        fun onData()
    }

    interface Model : MainDataSource
}