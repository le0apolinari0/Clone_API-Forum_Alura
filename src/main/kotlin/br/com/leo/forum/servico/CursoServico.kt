package br.com.leo.forum.servico


import br.com.leo.forum.modelo.Curso
import br.com.leo.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoServico (private val repository: CursoRepository) {

    fun  buscarPorId(id: Long): Curso{
        return repository.getReferenceById(id)
    }
}
