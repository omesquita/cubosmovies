package br.com.osnirmesquita.cubosmovies.data.remote.repository

import br.com.osnirmesquita.cubosmovies.data.mapper.GenreMapper
import br.com.osnirmesquita.cubosmovies.data.remote.Api
import br.com.osnirmesquita.cubosmovies.data.repository.GenreRepository
import br.com.osnirmesquita.cubosmovies.model.Genre
import io.reactivex.Observable

class GenreDataRepository(
    private val api: Api,
    private val mapper: GenreMapper
) : GenreRepository {

    override fun getGenreByIds(vararg genreIds: Long): Observable<List<Genre>> {
        return api.getGenres()
            .map { result ->
                result.genres
                    .filter { data -> genreIds.contains(data.id) }
                    .map { data -> mapper.mapFromData(data) }
            }
    }
}