package br.com.osnirmesquita.cubosmovies.di

import br.com.osnirmesquita.cubosmovies.features.model.Genre
import org.koin.dsl.module


val viewModule = module {
    single {
        listOf(
            Genre(28, "Ação"),
            Genre(18, "Drama"),
            Genre(14, "Fantasia"),
            Genre(878, "Ficção")
        )
    }
}