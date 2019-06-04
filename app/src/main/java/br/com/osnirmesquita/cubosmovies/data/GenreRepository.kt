package br.com.osnirmesquita.cubosmovies.data

import br.com.osnirmesquita.cubosmovies.data.result.Genre
import io.reactivex.Observable

interface GenreRepository {
    fun getGenresById(vararg ids: Int): Observable<List<Genre>>
}