package br.com.osnirmesquita.cubosmovies.data.remote.model

import com.squareup.moshi.Json

class MovieData(
    val id: Long,
    val title: String,
    @field:Json(name = "poster_path") val posterPath: String?,
    val overview: String = ""
)