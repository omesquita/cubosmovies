package br.com.osnirmesquita.cubosmovies.data

import br.com.osnirmesquita.cubosmovies.data.remote.Api
import br.com.osnirmesquita.cubosmovies.data.result.Movie
import io.reactivex.Observable

class MovieRepositoryImpl(private val api: Api) : MovieRepository {

    override fun getMovies(page: Int, genreId: Int): Observable<List<Movie>> {
        return api.getMovies(page, genreId).map { it.result }
    }

    override fun searchMovies(page: Int, query: String): Observable<List<Movie>> {
        return api.searchMovies(page, query).map { it.result }
    }
}