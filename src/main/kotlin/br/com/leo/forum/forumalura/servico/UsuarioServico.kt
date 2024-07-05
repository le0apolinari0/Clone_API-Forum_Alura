package br.com.leo.forum.forumalura.servico


import br.com.leo.forum.forumalura.modelo.Usuario
import org.springframework.stereotype.Service
import java.util.*
@Service
class UsuarioServico (final var usuarios: List<Usuario>) {
    init{
        val usuario = Usuario(
            id = 1,
            nome = "Leo Apolinario",
            email ="leo@outlook.com",
            telefone= "12988456892"
        )
        usuarios = Arrays.asList(usuario)
    }
    fun  buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter { curs ->
            curs.id == id
        }.findFirst().get()
    }
}


