package br.com.leo.forum.dto

data class Email(
    val subject: String,
    val text: String,
    val targetEmail: String
)