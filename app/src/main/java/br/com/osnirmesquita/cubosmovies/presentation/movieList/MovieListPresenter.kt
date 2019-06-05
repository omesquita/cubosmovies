package br.com.osnirmesquita.cubosmovies.presentation.movieList

import br.com.osnirmesquita.cubosmovies.data.repository.MovieRepository
import br.com.osnirmesquita.cubosmovies.presentation.base.BasePresenter

class MovieListPresenter(private val movieRepository: MovieRepository) : BasePresenter<MovieListContract.View>(),
    MovieListContract.Presenter {


}