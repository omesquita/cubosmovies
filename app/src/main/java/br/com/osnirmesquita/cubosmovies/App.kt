package br.com.osnirmesquita.cubosmovies

import android.app.Application
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        startKoin {

        }
    }
}