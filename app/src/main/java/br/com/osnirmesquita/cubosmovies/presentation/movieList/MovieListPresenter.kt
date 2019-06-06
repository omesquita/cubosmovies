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

    /**
     * This is the number of currentPage of result. Always start in page 1
     */
    private var currentPage = 1

    /**
     * This is the identity of genre the movies to be fetched
     * */
    private var genreId: Int = 0

    private val movies = mutableListOf<Movie>()

    override fun start(genreId: Int) {
        this.genreId = genreId

        this.fetchMovies()
    }

    private fun fetchMovies() {
        disposable.add(
            movieRepository.getMovies(currentPage, genreId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { sucess(it) },
                    { faill(it) }
                )
        )
    }

    private fun faill(it: Throwable?) {
        Timber.e(it)
    }

    private fun sucess(movies: List<Movie>) {
        this.movies.addAll(movies)

        getView().loadMovies(this.movies)
    }

    override fun next() {
        currentPage ++
        fetchMovies()
    }
}