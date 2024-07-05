package br.com.leo.forum.forumalura.modelo

import java.time.LocalDateTime

data class Topico(
    var id: Long?= null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    val status: StatusTopico = StatusTopico.TOPICO_ENCERRADO,
    val respostas: List<Respostas> = ArrayList()


)