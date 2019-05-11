package com.github.jaydeepw.assignment01.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class])
interface AppComponent {
    // fun inject(fragment: MainFragment)
    // fun inject(model: MainContractInterface.Model)
    // void inject(MyService service);
}
