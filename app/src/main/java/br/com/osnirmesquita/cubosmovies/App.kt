@file:Suppress("unused")

package br.com.osnirmesquita.cubosmovies

import android.app.Application
import br.com.osnirmesquita.cubosmovies.di.*
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        Timber.d("StartApp")
        startKoin {
            modules(
                listOf(
                    networkModule,
                    dataMapperModule,
                    repositoryModule,
                    presenterModule
                )
            )
        }
    }
}