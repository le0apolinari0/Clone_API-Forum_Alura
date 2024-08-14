package br.com.leo.forum.controller
import br.com.leo.forum.dto.*
import br.com.leo.forum.service.TopicoService
import jakarta.validation.Valid
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
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearerAuth")
class TopicoController (private val service: TopicoService) {

    @GetMapping
     fun listar(@RequestPart(required = false) nomeCurso : String?,
            @PageableDefault(size = 10, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoView> {
        return service.listar(nomeCurso, paginacao)
    }

        @GetMapping("/{id}")
        fun buscarPorId(@PathVariable id: Long)= service.buscarPorId(id)

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDeCurso>{
        return service.relatorioCurso()
    }

    @PostMapping
    @Transactional
     fun cadastrar(@RequestBody @Valid form:TopicoForm,
                   uriBuilder: UriComponentsBuilder):
            ResponseEntity<TopicoView>{
        val topicoView = service.cadastrar(form)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }
    @PutMapping
    @Transactional
     fun  atualizar(@RequestBody @Valid form: AtualizarTopicoForm): ResponseEntity<TopicoView>{
        val topicoView = service.atualizar(form)
         return ResponseEntity.ok(topicoView)
    }
    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
     fun deletar(@PathVariable id: Long){
       service.deletar(id)
     }
}