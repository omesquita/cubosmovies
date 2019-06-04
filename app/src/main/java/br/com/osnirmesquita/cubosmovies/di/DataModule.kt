package br.com.osnirmesquita.cubosmovies.di

import br.com.osnirmesquita.cubosmovies.data.GenreRepository
import br.com.osnirmesquita.cubosmovies.data.GenreRepositoryImpl
import br.com.osnirmesquita.cubosmovies.data.MovieRepository
import br.com.osnirmesquita.cubosmovies.data.MovieRepositoryImpl
import org.koin.dsl.module


val dataModule = module {
    //provide movie repository
    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    single<GenreRepository> {
        GenreRepositoryImpl(get())
    }
}