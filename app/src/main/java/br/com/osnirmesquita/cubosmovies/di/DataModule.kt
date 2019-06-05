package br.com.osnirmesquita.cubosmovies.di

import br.com.osnirmesquita.cubosmovies.data.mapper.GenreMapper
import br.com.osnirmesquita.cubosmovies.data.mapper.MovieMapper
import br.com.osnirmesquita.cubosmovies.data.remote.repository.GenreDataRepository
import br.com.osnirmesquita.cubosmovies.data.remote.repository.MovieDataRepository
import br.com.osnirmesquita.cubosmovies.data.repository.GenreRepository
import br.com.osnirmesquita.cubosmovies.data.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MovieRepository> {
        MovieDataRepository(get(), get())
    }

    single<GenreRepository> {
        GenreDataRepository(get(), get())
    }
}

val dataMapperModule = module {

    factory { GenreMapper() }

    factory { MovieMapper() }
}