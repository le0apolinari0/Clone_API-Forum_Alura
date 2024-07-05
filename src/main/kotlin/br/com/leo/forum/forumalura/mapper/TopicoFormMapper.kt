package br.com.leo.forum.forumalura.mapper

import br.com.leo.forum.forumalura.dto.TopicoForm
import br.com.leo.forum.forumalura.modelo.Topico
import br.com.leo.forum.forumalura.servico.CursoServico
import br.com.leo.forum.forumalura.servico.UsuarioServico
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper (
    private var cursoServico: CursoServico,
    private var usuarioServico: UsuarioServico
 ): Mapper<TopicoForm, Topico> {
     override fun map(t: TopicoForm): Topico {
          return  Topico(
                titulo = t.titulo,
                mensagem = t.mensagem,
                curso = cursoServico.buscarPorId(t.idCurso),
                autor = usuarioServico.buscarPorId(t.idAutor)
            )
    }


}
