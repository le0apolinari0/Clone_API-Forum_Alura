package br.com.leo.forum.forumalura.mapper

interface Mapper < T, U> {
    fun map(t: T): U
}