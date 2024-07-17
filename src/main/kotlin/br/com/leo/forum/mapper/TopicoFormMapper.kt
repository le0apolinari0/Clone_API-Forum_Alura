package br.com.leo.forum.mapper

import br.com.leo.forum.modelo.Topico
import br.com.leo.forum.servico.CursoServico
import br.com.leo.forum.servico.UsuarioServico
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper (
    private var cursoServico: CursoServico,
    private var usuarioServico: UsuarioServico
 ): Mapper<br.com.leo.forum.dto.TopicoForm, Topico> {
     override fun map(t: br.com.leo.forum.dto.TopicoForm): Topico {
          return  Topico(
                titulo = t.titulo,
                mensagem = t.mensagem,
                curso = cursoServico.buscarPorId(t.idCurso),
                autor = usuarioServico.buscarPorId(t.idAutor)
            )
    }


}
