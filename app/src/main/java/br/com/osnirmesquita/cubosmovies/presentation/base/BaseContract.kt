package br.com.osnirmesquita.cubosmovies.presentation.base

interface BaseContract {

    interface View

    interface Presenter<in V : View> {
        fun attachView(view: V)

        fun dettachView()
    }
}