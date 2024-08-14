package br.com.leo.forum.model

import br.com.leo.forum.modelo.Usuario

object UsuarioTest {
    fun build() = Usuario(id = 1, nome = "Joao", email = "jvc.martins", telefone = "12988456556", password = "123456")
    fun buildToToken() = Usuario(nome = "Ana da Silva", email = "ana@email.com",telefone = "12988456556", password = "123456")
}