package br.com.leo.forum.dto
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class AtualizarTopicoForm (
    @field:NotNull val id: Long,
    @field:NotEmpty val titulo: String,
    @field:NotEmpty val mensagem: String
    )
