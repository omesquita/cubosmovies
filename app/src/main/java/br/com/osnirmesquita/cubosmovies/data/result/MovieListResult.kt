package br.com.osnirmesquita.cubosmovies.data.result

import com.squareup.moshi.Json

data class MovieListResult(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "total_results") val totalResults: Long,
    @field:Json(name = "total_pages") val totalPages: Long,
    @field:Json(name = "results") val result: List<Movie>
)

data class Movie(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "poster_path") val pathImage: String
)