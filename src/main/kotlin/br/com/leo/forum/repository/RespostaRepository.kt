package br.com.leo.forum.repository

import br.com.leo.forum.modelo.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository : JpaRepository<Resposta, Long>