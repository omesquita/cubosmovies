package br.com.osnirmesquita.cubosmovies.data

import br.com.osnirmesquita.cubosmovies.data.result.Movie
import io.reactivex.Observable

/**
 * Repository contract
 * */
interface MovieRepository {

    fun getMovies(page: Int, genreId: Int): Observable<List<Movie>>

    fun searchMovies(page: Int, query: String): Observable<List<Movie>>
}