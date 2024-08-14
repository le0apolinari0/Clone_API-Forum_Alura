package br.com.leo.forum.service


import br.com.leo.forum.modelo.Curso
import br.com.leo.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService (private val repository: CursoRepository) {

    fun  buscarPorId(id: Long): Curso{
        return repository.getOne(id)
    }
}
