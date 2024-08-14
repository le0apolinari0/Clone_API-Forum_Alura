package br.com.leo.forum.mapper

import br.com.leo.forum.dto.TopicoForm
import br.com.leo.forum.modelo.Topico
import br.com.leo.forum.service.CursoService
import br.com.leo.forum.service.UsuarioService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TopicoFormMapper (
    private var cursoServico: CursoService,
    private var usuarioServico: UsuarioService
 ): Mapper<TopicoForm, Topico> {
    override fun map(t: TopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoServico.buscarPorId(t.idCurso),
            autor = usuarioServico.buscarPorId(t.idAutor),
            dataAlteracao = LocalDate.now()
        )
    }
}


