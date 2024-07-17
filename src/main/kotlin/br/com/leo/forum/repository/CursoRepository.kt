package br.com.leo.forum.repository


import br.com.leo.forum.modelo.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long> {
}