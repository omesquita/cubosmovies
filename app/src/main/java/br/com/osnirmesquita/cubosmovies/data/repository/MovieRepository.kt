package br.com.osnirmesquita.cubosmovies.data.repository

import br.com.osnirmesquita.cubosmovies.model.Movie
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository {

    fun getMovies(page: Int, genreId: Int): Observable<List<Movie>>

    fun searchMovies(page: Int, query: String): Observable<List<Movie>>

    fun getMovie(id: Int): Single<Movie>
}