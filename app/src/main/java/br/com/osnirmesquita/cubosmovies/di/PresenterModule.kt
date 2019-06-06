package br.com.osnirmesquita.cubosmovies.di

import br.com.osnirmesquita.cubosmovies.presentation.main.MainContract
import br.com.osnirmesquita.cubosmovies.presentation.main.MainPresenter
import br.com.osnirmesquita.cubosmovies.presentation.movieDetail.MovieDetailContract
import br.com.osnirmesquita.cubosmovies.presentation.movieDetail.MovieDetailPresenter
import br.com.osnirmesquita.cubosmovies.presentation.movieList.MovieListContract
import br.com.osnirmesquita.cubosmovies.presentation.movieList.MovieListPresenter
import org.koin.dsl.module


val presenterModule = module {

    factory<MainContract.Presenter> {
        MainPresenter(get())
    }

    factory<MovieListContract.Presenter> {
        MovieListPresenter(get())
    }

    factory<MovieDetailContract.Presenter> {
        MovieDetailPresenter(get())
    }
}