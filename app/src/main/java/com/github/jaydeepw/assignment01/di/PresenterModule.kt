package com.github.jaydeepw.assignment01.di

import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.db.AlbumRepository
import com.github.jaydeepw.assignment01.presenters.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PresenterModule(
    _view: MainContractInterface.View,
    _albumRepository: AlbumRepository
) {

    var view = _view
    var albumRepository = _albumRepository

    @Provides
    @Singleton
    internal fun providesMainPresenter(): MainPresenter {
        return MainPresenter(view, albumRepository)
    }
}