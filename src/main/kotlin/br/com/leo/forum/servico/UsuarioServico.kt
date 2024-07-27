package br.com.leo.forum.servico



import br.com.leo.forum.modelo.Usuario
import br.com.leo.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class UsuarioServico (
    private val repository: UsuarioRepository): UserDetailsService {

    fun  buscarPorId(id: Long): Usuario {
        return repository.getReferenceById(id)
    }
    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }
}


