package br.com.osnirmesquita.cubosmovies.data.remote.model

class MovieData(
    val id: Long,
    val title: String,
    val posterPath: String,
    val overview: String = ""
)