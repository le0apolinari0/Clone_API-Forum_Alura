package br.com.leo.forum.forumalura.mapper

import br.com.leo.forum.forumalura.dto.TopicoView
import br.com.leo.forum.forumalura.modelo.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView>{
    override fun map(t: Topico): TopicoView {
       return TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao


        )

    }
}