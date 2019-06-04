package br.com.osnirmesquita.cubosmovies.data

import br.com.osnirmesquita.cubosmovies.data.remote.Api
import br.com.osnirmesquita.cubosmovies.data.result.Genre
import io.reactivex.Observable

class GenreRepositoryImpl(private val api: Api) : GenreRepository {

    override fun getGenresById(vararg ids: Int): Observable<List<Genre>> {
        return api.getGenres()
            .map {
                it.result
                    .filter { genre -> ids.contains(genre.id) }
            }
    }
}