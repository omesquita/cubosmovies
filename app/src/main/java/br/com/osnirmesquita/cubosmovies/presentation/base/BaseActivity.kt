package br.com.osnirmesquita.cubosmovies.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import java.lang.ClassCastException

abstract class BaseActivity<T, V> : AppCompatActivity(), BaseContract.View
        where T : BaseContract.Presenter<V>,
              V : BaseContract.View {

    abstract fun getPresenter(): T

    abstract fun destroyPresenter()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        try {
            getPresenter().attachView(this as V)
        } catch (e: ClassCastException) {
            Timber.e(e)
        }


        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy()")
        getPresenter().detachView()
        getPresenter().destroyView()
        destroyPresenter()
    }
}