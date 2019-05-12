package com.github.jaydeepw.assignment01.di

import com.github.jaydeepw.assignment01.presenters.MainPresenter
import com.github.jaydeepw.assignment01.views.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    PresenterModule::class,
    ModelsModule::class
])
interface FragmentComponent {
    fun inject(fragment: MainFragment)
    fun inject(presenter: MainPresenter)
}
