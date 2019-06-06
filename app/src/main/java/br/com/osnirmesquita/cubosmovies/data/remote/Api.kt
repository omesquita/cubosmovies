package br.com.osnirmesquita.cubosmovies.data.remote

import br.com.osnirmesquita.cubosmovies.data.remote.model.GenreDataResult
import br.com.osnirmesquita.cubosmovies.data.remote.model.MovieData
import br.com.osnirmesquita.cubosmovies.data.remote.model.MovieDataResult
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    /**
     * Get a list of genres
     * */
    @GET("3/genre/movie/list")
    fun getGenres(): Observable<GenreDataResult>

    /**
     * Get a list movie sort by popularity and that is of the genre defined in params
     *
     * @param [page] page number to result
     * @param [genres] genre code
     *
     * @return [MovieDataResult]
     * */
    @GET("3/discover/movie?sort_by=popularity.desc")
    fun getMovies(
        @Query("page") page: Int,
        @Query("with_genres") genres: Int
    ): Observable<MovieDataResult>

    /**
     * Search a movie list
     * @param [page] page number to result
     * @param [query] a string with a query to search
     *
     * @return [MovieDataResult]
     * */
    @GET("3/search/movie")
    fun searchMovies(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Observable<MovieDataResult>

    /**
     * Get a movie by a id
     * @param [id] the identity movie
     *
     * @return [MovieData]
     * */
    @GET("3/movie")
    fun getMovie(@Query("id") id: Int): Single<MovieData>
}