package br.com.osnirmesquita.cubosmovies.presentation.base

interface BaseContract {

    interface View

    interface Presenter<in T : View> {

        fun attachView(view: T)

        fun destroyView()

        fun detachView()
    }
}