package br.com.leo.forum.mapper


import br.com.leo.forum.modelo.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, br.com.leo.forum.dto.TopicoView>{
    override fun map(t: Topico): br.com.leo.forum.dto.TopicoView {
       return br.com.leo.forum.dto.TopicoView(
           id = t.id,
           titulo = t.titulo,
           mensagem = t.mensagem,
           dataCriacao = t.dataCriacao


       )

    }
}