package br.com.leo.forum.forumalura.dto

import java.time.LocalDateTime

data class TopicoView (
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime


)

