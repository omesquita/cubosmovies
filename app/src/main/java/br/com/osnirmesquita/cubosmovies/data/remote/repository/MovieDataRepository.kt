package br.com.osnirmesquita.cubosmovies.data.remote.repository

import br.com.osnirmesquita.cubosmovies.data.mapper.MovieMapper
import br.com.osnirmesquita.cubosmovies.data.remote.Api
import br.com.osnirmesquita.cubosmovies.data.repository.MovieRepository
import br.com.osnirmesquita.cubosmovies.model.Movie
import io.reactivex.Observable

class MovieDataRepository(
    private val api: Api,
    private val mapper: MovieMapper
) : MovieRepository {

    override fun getMovies(page: Int, genreId: Int): Observable<List<Movie>> {
        return api.getMovies(page, genreId)
            .map { result ->
                result.result
                    .map { data -> mapper.mapFromData(data) }
            }
    }

    override fun searchMovies(page: Int, query: String): Observable<List<Movie>> {
        return api.searchMovies(page, query)
            .map { result ->
                result.result
                    .map { data -> mapper.mapFromData(data) }
            }
    }
}