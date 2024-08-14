package br.com.leo.forum.config
import br.com.leo.forum.security.JwtFiltroDeAutenticacao
import br.com.leo.forum.security.JwtFiltroLogin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val configuration: AuthenticationConfiguration,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil
){
    @Bean
    fun securityFilterChain (http: HttpSecurity): SecurityFilterChain  {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/topicos").hasAuthority("LEITURA_ESCRITA")
                    .requestMatchers("/respostas").hasAuthority("LEITURA_ESCRITA")
                    .requestMatchers(HttpMethod.GET, "/relatorios").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .addFilterBefore(JwtFiltroLogin(authManager = configuration.authenticationManager, jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            .addFilterBefore(JwtFiltroDeAutenticacao(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.build()
    }

        @Bean
        fun bCryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    fun configure(auth: AuthenticationManagerBuilder?) {
            auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
        }
    }
