package br.com.osnirmesquita.cubosmovies.data.mapper

import br.com.osnirmesquita.cubosmovies.data.remote.model.MovieData
import br.com.osnirmesquita.cubosmovies.model.Movie

class MovieMapper : Mapper<MovieData, Movie> {

    override fun mapFromData(from: MovieData): Movie {
        return Movie(from.id, from.title, from.posterPath, from.overview)
    }
}