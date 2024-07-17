package br.com.leo.forum.servico



import br.com.leo.forum.modelo.Usuario
import br.com.leo.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioServico ( private val repository: UsuarioRepository) {

    fun  buscarPorId(id: Long): Usuario {
        return repository.getReferenceById(id)
    }
}


