package br.com.osnirmesquita.cubosmovies.data.result

import com.squareup.moshi.Json

data class GenreResult(
    @field:Json(name = "genres") val result: List<Genre>
)

data class Genre(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String
)