package br.com.leo.forum.controller

import br.com.leo.forum.modelo.Resposta
import br.com.leo.forum.service.RespostaService
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearerAuth")
class RespostaController(
    private val respostaService: RespostaService
) {

    @PostMapping
    fun responder(@RequestBody resposta: Resposta) {
        return respostaService.responder(resposta)
    }
}