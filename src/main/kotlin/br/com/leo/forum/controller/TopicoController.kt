package br.com.leo.forum.controller
import br.com.leo.forum.dto.AtualizarTopicoForm
import br.com.leo.forum.dto.TopicoForm
import br.com.leo.forum.dto.TopicoView
import br.com.leo.forum.servico.TopicoServico
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/topicos")
class TopicoController (private val servico: TopicoServico) {

    @GetMapping
    @Cacheable("topicos")
     fun listar(@RequestPart(required = false) nomeCurso : String?,
            @PageableDefault(size = 10, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoView>{
        return servico.listar(nomeCurso,paginacao)
    }
        @GetMapping("/{id}")
        fun buscarPorId(@PathVariable id: Long):TopicoView {
            return servico.buscarPorId(id)
        }
    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
     fun cadastrar(@RequestBody @Valid form:TopicoForm,
                   uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView>{
        val topicoView = servico.cadastrar(form)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }
    @PutMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
     fun  atualizar(@RequestBody @Valid form: AtualizarTopicoForm): ResponseEntity<TopicoView>{
        val topicoView = servico.atualizar(form)
        return return ResponseEntity.ok(topicoView)
    }
    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["topicos"], allEntries = true)
     fun deletar(@PathVariable id: Long){
       servico.deletar(id)
     }
}