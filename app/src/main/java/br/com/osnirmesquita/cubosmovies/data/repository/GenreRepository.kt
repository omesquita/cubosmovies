package br.com.osnirmesquita.cubosmovies.data.repository

import br.com.osnirmesquita.cubosmovies.model.Genre
import io.reactivex.Observable

interface GenreRepository {
    fun getGenreByIds(vararg genreIds: Long): Observable<List<Genre>>
}