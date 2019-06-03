package br.com.osnirmesquita.cubosmovies.data.result

import com.squareup.moshi.Json

data class GenderResult(
    @field:Json(name = "genres") val result: List<Gender>
)

data class Gender(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String
)