package br.com.leo.forum.mapper

interface Mapper < T, U> {
    fun map(t: T): U
}