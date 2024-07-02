package br.com.leo.forum.forumalura

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class TesteController {
    @GetMapping
    fun hello() : String{
        return "Hello word kandkasn"
    }
}