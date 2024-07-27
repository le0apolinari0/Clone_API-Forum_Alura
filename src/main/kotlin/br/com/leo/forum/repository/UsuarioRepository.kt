package br.com.leo.forum.repository



import br.com.leo.forum.modelo.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {
    abstract fun findByEmail(username: String?): Usuario?
}