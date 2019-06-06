package br.com.osnirmesquita.cubosmovies.presentation.movieList

import br.com.osnirmesquita.cubosmovies.data.repository.MovieRepository
import br.com.osnirmesquita.cubosmovies.model.Movie
import br.com.osnirmesquita.cubosmovies.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieListPresenter(
    private val movieRepository: MovieRepository
) : BasePresenter<MovieListContract.View>(), MovieListContract.Presenter {

    private var currentPage = 1 //This is the number of currentPage of result. Always start in page 1
    private var genreId: Int = 0 //This is the identity of genre the movies to be fetched
    private var modeSearch = false //Flag to indicate when it is movies searched
    private var query: String = "" //Query to search movies
    private var movies = mutableListOf<Movie>() //List of movies

    /**
     * When start presenter, fetch movies and store genre to be searched
     * */
    override fun start(genreId: Int) {
        this.genreId = genreId

        this.fetchMovies()
    }

    /**
     * Fetch movies using the repository, tracker event when success and fail
     * */
    private fun fetchMovies() {
        disposable.add(
            movieRepository.getMovies(currentPage, genreId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { success(it) },
                    { fail(it) }
                )
        )
    }

    private fun success(movies: List<Movie>) {
        this.movies.addAll(movies)

        getView().loadMovies(this.movies)
    }

    private fun fail(ex: Throwable?) {
        Timber.e(ex)
        getView().showFailSearch("Ocorreu um erro ao buscar os filmes")
    }

    override fun search(query: String) {
        this.modeSearch = true

        if (this.query != query) {
            this.query = query
            clearParams()
        }

        fetchSearch()
    }

    /**
     * Clear the params of current page and movie list
     * */
    private fun clearParams() {
        this.currentPage = 1
        this.movies = mutableListOf()
    }

    /**
     * When search is closed reset params and fetch movies
     * */
    override fun closeSearch() {
        modeSearch = false
        query = ""
        clearParams()

        fetchMovies()
    }

    private fun fetchSearch() {
        this.disposable.add(
            this.movieRepository.searchMovies(this.currentPage, this.query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { success(it) },
                    { fail(it) }
                )
        )
    }

    override fun next() {
        currentPage++
        if (modeSearch) {
            fetchSearch()
        } else {
            fetchMovies()
        }
    }

    override fun movieClicked(movie: Movie) {
        getView().showMovieDetail(movie)
    }
}