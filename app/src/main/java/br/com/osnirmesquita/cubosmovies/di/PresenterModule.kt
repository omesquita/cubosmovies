package br.com.osnirmesquita.cubosmovies.di

import br.com.osnirmesquita.cubosmovies.presentation.main.MainContract
import br.com.osnirmesquita.cubosmovies.presentation.main.MainPresenter
import org.koin.dsl.module


val presenterModule = module {

    factory<MainContract.Presenter> {
        MainPresenter(get())
    }
}