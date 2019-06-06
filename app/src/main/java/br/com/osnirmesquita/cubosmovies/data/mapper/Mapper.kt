package br.com.osnirmesquita.cubosmovies.data.mapper

interface Mapper<F, T> {
    fun mapFromData(from: F): T
}