package br.com.leo.forum.repository




import br.com.leo.forum.dto.TopicoPorCategoriaDeCursoDto
import br.com.leo.forum.modelo.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico, Long> {
    fun findByCursoNome(nomeCurso: String,paginacao: Pageable):Page<Topico>

    @Query("SELECT new br.com.leo.forum.dto.TopicoPorCategoriaDeCursoDto(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria ")
    fun relatotio(): List<TopicoPorCategoriaDeCursoDto>
}

