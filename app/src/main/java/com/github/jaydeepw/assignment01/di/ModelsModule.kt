package com.github.jaydeepw.assignment01.di

import com.github.jaydeepw.assignment01.models.datasource.network.MainNetworkModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ModelsModule() {
    @Provides
    @Singleton
    internal fun providesMainNetworkModel(): MainNetworkModel {
        return MainNetworkModel()
    }
}