package br.com.leo.forum.exception

import br.com.leo.forum.dto.ErrorDeView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class TratamentoDeException {
     @ExceptionHandler( NotFoundException::class)
     @ResponseStatus(HttpStatus.NOT_FOUND)
    fun tratandoErrorNotFound(exception: NotFoundException,
                              request: HttpServletRequest): ErrorDeView {
     return ErrorDeView(
         status = HttpStatus.NOT_FOUND.value(),
         error = HttpStatus.NOT_FOUND.name,
         message = exception.message,
         path = request.servletPath
     )
    }
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun tratandoErrorDoServido( exception: Exception,
                          request: HttpServletRequest): ErrorDeView {
        return ErrorDeView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validandoErrorDoServido( exception:MethodArgumentNotValidException,
                                request: HttpServletRequest): ErrorDeView {
        val mensagemError = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach{
            er -> mensagemError.put(er.field, er.defaultMessage)
        }
        return ErrorDeView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = mensagemError.toString(),
            path = request.servletPath
        )
    }

}