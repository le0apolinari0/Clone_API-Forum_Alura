package br.com.leo.forum.dto

import java.time.LocalDateTime

data class ErrorDeView (
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
