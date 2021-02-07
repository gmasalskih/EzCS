package ru.gmasalskikh.ezcs

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.gmasalskikh.ezcs.di.appStateModule
import ru.gmasalskikh.ezcs.di.viewModelModule
import ru.gmasalskikh.ezcs.di.providerModule
import ru.gmasalskikh.ezcs.di.viewStateModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appStateModule,
                viewModelModule,
                providerModule,
                viewStateModule
            )
        }
    }
}