package br.com.osnirmesquita.cubosmovies.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

abstract class BaseActivity<P, V> : AppCompatActivity(), BaseContract.View
        where P : BaseContract.Presenter<V>,
              V : BaseContract.View {

    /**
     * Field to store the presenter in the all activities
     * */
    protected var presenter: P? = null
        //set presenter only if none be have instantiated
        set(value) {
            if (field == null) {
                field = value
            }
        }

    /**
     *
     * Function to forces the activity to extend the class to make the presenter bind
     *
     * @return [P] type to presenter
     * */
    protected abstract fun bindPresenter(): P

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Timber.d("onCreate()")
        //When starting onCreate calls the function to create Presenter and attach the view
        presenter = bindPresenter()
        presenter?.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy()")
        presenter?.detachView()
        presenter?.destroyView()
        presenter = null
    }
}