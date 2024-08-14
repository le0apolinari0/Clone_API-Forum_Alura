package br.com.leo.forum.mapper


import br.com.leo.forum.dto.TopicoView
import br.com.leo.forum.modelo.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico,TopicoView>{
    override fun map(t: Topico):TopicoView {
       return TopicoView(
           id = t.id,
           titulo = t.titulo,
           mensagem = t.mensagem,
           dataCriacao = t.dataCriacao,
           status = t.status,
           dataAlteracao = t.dataAlteracao
       )

    }
}