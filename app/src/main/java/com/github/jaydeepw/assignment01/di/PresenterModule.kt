package com.github.jaydeepw.assignment01.di

import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.db.dao.AlbumDao
import com.github.jaydeepw.assignment01.presenters.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PresenterModule(_view: MainContractInterface.View, _albumDao: AlbumDao) {

    var view = _view
    var albumDao = _albumDao

    @Provides
    @Singleton
    internal fun providesMainPresenter(): MainPresenter {
        return MainPresenter(view, albumDao)
    }
}