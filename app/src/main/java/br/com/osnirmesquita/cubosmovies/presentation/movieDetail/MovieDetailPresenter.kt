package br.com.osnirmesquita.cubosmovies.presentation.movieDetail

import br.com.osnirmesquita.cubosmovies.data.repository.MovieRepository
import br.com.osnirmesquita.cubosmovies.model.Movie
import br.com.osnirmesquita.cubosmovies.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class MovieDetailPresenter(
    private val movieRepository: MovieRepository
) : BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {

    override fun start(movieId: Long) {
        disposable.add(
            movieRepository.getMovie(movieId.toInt())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { getView().hideContent() }
                .subscribe(
                    {
                        sucess(it)
                    },
                    {
                        faill(it)
                    })
        )
    }

    private fun faill(it: Throwable?) {
        getView().showError("Ocorreu um erro ao buscar os dados.")
        Timber.e(it)
    }

    private fun sucess(movie: Movie) {
        getView().setMovie(movie)
        getView().showContent()
    }
}