package br.com.osnirmesquita.cubosmovies.presentation.movieList

import br.com.osnirmesquita.cubosmovies.presentation.base.BaseContract

interface MovieListContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}