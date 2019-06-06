package br.com.osnirmesquita.cubosmovies.presentation.movieDetail

import br.com.osnirmesquita.cubosmovies.model.Movie
import br.com.osnirmesquita.cubosmovies.presentation.base.BaseContract

interface MovieDetailContract {

    interface View : BaseContract.View {
        fun setMovie(movie: Movie)

        fun showContent()

        fun hideContent()

        fun showError(message: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun start(movieId: Long)
    }
}