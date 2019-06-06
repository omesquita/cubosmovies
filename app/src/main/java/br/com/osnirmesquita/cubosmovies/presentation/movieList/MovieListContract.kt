package br.com.osnirmesquita.cubosmovies.presentation.movieList

import br.com.osnirmesquita.cubosmovies.model.Movie
import br.com.osnirmesquita.cubosmovies.presentation.base.BaseContract

interface MovieListContract {

    interface View : BaseContract.View {
        fun loadMovies(movies: List<Movie>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun next()

        fun start(genreId: Int)
    }
}