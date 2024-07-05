package br.com.leo.forum.forumalura.modelo

import java.time.LocalDateTime

data class Respostas (
    val id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val autor: Usuario,
    val topico: Topico,
    val status: StatusTopico = StatusTopico.TOPICO_ENCERRADO,
    val respostas: List<Respostas> = ArrayList()
)
