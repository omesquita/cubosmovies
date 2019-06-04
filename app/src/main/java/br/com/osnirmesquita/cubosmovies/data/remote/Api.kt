package br.com.osnirmesquita.cubosmovies.data.remote

import br.com.osnirmesquita.cubosmovies.data.result.GenreResult
import br.com.osnirmesquita.cubosmovies.data.result.MovieListResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    /**
     * Get a list of genres
     * */
    @GET("3/genre/movie/list")
    fun getGenders(): Observable<GenreResult>

    /**
     * Get a list movie sort by popularity and that is of the genre defined in params
     *
     * @param [page] page number to result
     * @param [genres] genre code
     *
     * @return [MovieListResult]
     * */
    @GET("3/discover/movie?sort_by=popularity.desc")
    fun getMovies(
        @Query("page") page: Int,
        @Query("with_genres") genres: Int
    ): Observable<MovieListResult>

    /**
     * Search a movie list
     * @param [page] page number to result
     * @param [query] a string with a query to search
     *
     * @return [MovieListResult]
     * */
    @GET("3/search/movie")
    fun searchMovies(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Observable<MovieListResult>
}