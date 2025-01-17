package br.com.osnirmesquita.cubosmovies.presentation.main

import br.com.osnirmesquita.cubosmovies.data.repository.GenreRepository
import br.com.osnirmesquita.cubosmovies.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter(
    private val genreRepository: GenreRepository
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun start() {
        Timber.d("start")

        disposable.add(
            genreRepository.getGenreByIds(28, 14, 18, 878)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        getView().setUpTabs(it)
                    },
                    {
                        Timber.e(it)
                    })
        )
    }
}