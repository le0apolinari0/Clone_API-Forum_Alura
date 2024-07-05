package br.com.leo.forum.forumalura.servico

import br.com.leo.forum.forumalura.modelo.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoServico (var cursos: List<Curso>) {
    init{
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Progamação"
        )
        cursos = Arrays.asList(curso)
    }
    fun  buscarPorId(id: Long): Curso{
        return cursos.stream().filter{
                curs -> curs.id == id
        }.findFirst().get()
    }
}
