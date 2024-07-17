package br.com.leo.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class TopicoForm (
    @field:NotEmpty ( message = "O Campo titulo deve conter entre 2 e 50 letras") @field:Size(
        min = 2, max= 50, message = "O Campo titulo esta em Branco")val titulo: String,
    @field:NotEmpty ( message = "O Campo mensagem deve conter entre 2 e 100 letras") @field:Size(
        min = 2, max= 100, message = "O Campo mensagem esta em branco")  val mensagem: String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
)

