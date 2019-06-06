package br.com.osnirmesquita.cubosmovies.presentation.base

import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    private var _view: V? = null
    protected val disposable = CompositeDisposable()

    protected fun getView(): V {
        return _view ?: throw  ViewNotAttachedException()
    }

    override fun attachView(view: V) {
        Timber.d("attachView()")
        this._view = view
    }

    override fun dettachView() {
        Timber.d("dettachView()")
        this.disposable.clear()
        this._view = null
    }

    class ViewNotAttachedException : RuntimeException()
}