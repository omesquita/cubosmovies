package br.com.osnirmesquita.cubosmovies.data.remote.model

class MovieDataResult(
    val page: Int,
    val totalResults: Long,
    val totalPages: Long,
    val result: List<MovieData>
)