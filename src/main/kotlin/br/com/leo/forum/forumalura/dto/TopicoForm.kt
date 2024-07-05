package br.com.leo.forum.forumalura.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class TopicoForm (
    @field:NotEmpty @field:Size( min = 2, max= 50)val titulo: String,
    @field:NotEmpty @field:Size( min = 2, max= 100)  val mensagem: String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
)

