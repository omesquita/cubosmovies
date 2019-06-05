package br.com.osnirmesquita.cubosmovies.presentation.base

import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    protected val disposable = CompositeDisposable()

    /**
     * Store the view in the Presenter
     * */
    protected var view: V? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }

    /**
     * Attach view in the presenter
     *
     * @param [view] the View to be attached
     * */
    override fun attachView(view: V) {
        Timber.d("attachView(${view.javaClass.canonicalName})")
        this.view = view
    }

    /**
     * Detach view of the presenter
     * */
    override fun detachView() {
        Timber.d("detachView()")
        this.view = null
    }

    override fun destroyView() {
        Timber.d("destroyView()")
        this.disposable.clear()
    }

}