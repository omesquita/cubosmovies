package br.com.osnirmesquita.cubosmovies.data.mapper

import br.com.osnirmesquita.cubosmovies.data.remote.model.GenreData
import br.com.osnirmesquita.cubosmovies.model.Genre

class GenreMapper : Mapper<GenreData, Genre> {
    override fun mapFromData(from: GenreData): Genre {
        return Genre(from.id, from.name)
    }
}