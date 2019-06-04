package br.com.osnirmesquita.cubosmovies.data

import br.com.osnirmesquita.cubosmovies.data.result.GenderResult
import br.com.osnirmesquita.cubosmovies.data.result.MovieListResult
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("3/genre/movie/list")
    fun getGenders(): Observable<GenderResult>

    @GET("3/movie/popular")
    fun getMovies() : Observable<MovieListResult>
}