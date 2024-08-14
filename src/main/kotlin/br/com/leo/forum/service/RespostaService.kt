package br.com.leo.forum.service

import br.com.leo.forum.modelo.Resposta
import br.com.leo.forum.repository.RespostaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService,
    private val usuarioService: UsuarioService
) {
    @Transactional
    fun responder(resposta: Resposta) {
        respostaRepository.save(resposta)

        val usuario = usuarioService.buscarPorId(requireNotNull(resposta.autor.id))

        emailService.enviar(
            subject = "Alura - Tópico Respondido",
            text = "Você recebeu uma resposta no seu tópico",
            targetEmail = usuario.email
        )
    }
}