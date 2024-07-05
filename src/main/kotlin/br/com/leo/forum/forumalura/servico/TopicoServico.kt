package br.com.leo.forum.forumalura.servico

import br.com.leo.forum.forumalura.dto.TopicoForm
import br.com.leo.forum.forumalura.dto.TopicoView
import br.com.leo.forum.forumalura.dto.AtualizarTopicoForm
import br.com.leo.forum.forumalura.mapper.TopicoFormMapper
import br.com.leo.forum.forumalura.mapper.TopicoViewMapper
import br.com.leo.forum.forumalura.modelo.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoServico (
    private var atribuTopicos: List<Topico> = ArrayList(),
    private var topicoViewMapper: TopicoViewMapper,
    private var topicoFormMapper: TopicoFormMapper
){


    fun listar() : List<TopicoView>{
     return atribuTopicos.stream().map {
         top -> topicoViewMapper.map(top) }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = atribuTopicos.stream().filter { topic ->
            topic.id == id
        }.findFirst().get()
        return topicoViewMapper.map(topico )
     }

    fun cadastrar(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = atribuTopicos.size.toLong() +1
       atribuTopicos = atribuTopicos.plus(topico)
        return topicoViewMapper.map(topico)

    }

    fun atualizar(form: AtualizarTopicoForm):TopicoView {
        val topico = atribuTopicos.stream().filter{
            t -> t.id == form.id }.findFirst().get()
        val topicoAtualizado =Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
            )
         atribuTopicos = atribuTopicos.minus(topico).plus(topicoAtualizado)
        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = atribuTopicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()
        atribuTopicos = atribuTopicos.minus(topico)
    }
}