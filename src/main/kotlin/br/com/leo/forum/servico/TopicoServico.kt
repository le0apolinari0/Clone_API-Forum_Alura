package br.com.leo.forum.servico


import br.com.leo.forum.dto.AtualizarTopicoForm
import br.com.leo.forum.dto.TopicoForm
import br.com.leo.forum.dto.TopicoPorCategoriaDeCursoDto
import br.com.leo.forum.dto.TopicoView
import br.com.leo.forum.exception.NotFoundException
import br.com.leo.forum.mapper.TopicoFormMapper
import br.com.leo.forum.mapper.TopicoViewMapper
import br.com.leo.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoServico (
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMensagem: String = "As Informações Digitadas Estão Incorretas !",

){


    fun listar(nomeCurso: String?,
               paginacao:Pageable): Page<TopicoView>{
        val topicos = if (nomeCurso== null){
            repository.findAll(paginacao)
        } else{
            repository.findByCursoNome(nomeCurso,paginacao)
        }
     return topicos.map {
         top -> topicoViewMapper.map(top) }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMensagem) }
        return topicoViewMapper.map(topico )
     }

    fun cadastrar(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)

    }

    fun atualizar(form:AtualizarTopicoForm):TopicoView {
        var topico = repository.findById(form.id)
            .orElseThrow{NotFoundException(notFoundMensagem) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorioCurso(): List<TopicoPorCategoriaDeCursoDto> {
        return repository.relatotio()
    }
}