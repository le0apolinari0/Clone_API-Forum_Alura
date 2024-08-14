package br.com.leo.forum.service


import br.com.leo.forum.dto.*
import br.com.leo.forum.exception.NotFoundException
import br.com.leo.forum.mapper.TopicoFormMapper
import br.com.leo.forum.mapper.TopicoViewMapper
import br.com.leo.forum.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService (
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMensagem: String = "As Informações Digitadas Estão Incorretas !",

){

    @Cacheable(cacheNames = ["Topicos"], key = "#root.method.name")
    fun listar(nomeCurso: String?,
               paginacao:Pageable
    ): Page<TopicoView>{
        val topicos = nomeCurso?.let {
            repository.findByCursoNome(nomeCurso, paginacao)
        } ?: repository.findAll(paginacao)

        return topicos.map { top ->
            topicoViewMapper.map(top)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMensagem) }
        return topicoViewMapper.map(topico )
     }
    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun cadastrar(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)

    }
    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun atualizar(form:AtualizarTopicoForm):TopicoView {
        var topico = repository.findById(form.id)
            .orElseThrow{NotFoundException(notFoundMensagem) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorioCurso(): List<TopicoPorCategoriaDeCurso> {
        return repository.relatorio()
    }
}