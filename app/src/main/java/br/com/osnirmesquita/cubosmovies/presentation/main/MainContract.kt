package br.com.osnirmesquita.cubosmovies.presentation.main

import br.com.osnirmesquita.cubosmovies.model.Genre
import br.com.osnirmesquita.cubosmovies.presentation.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun setupTabs(genres: List<Genre>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun start()
    }
}