package br.com.osnirmesquita.cubosmovies.data

import br.com.osnirmesquita.cubosmovies.data.result.GenreResult
import br.com.osnirmesquita.cubosmovies.data.result.MovieListResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("3/genre/movie/list")
    fun getGenders(): Observable<GenreResult>

    @GET("3/movie/popular")
    fun getMovies(@Query("page") page: Int) : Observable<MovieListResult>
}