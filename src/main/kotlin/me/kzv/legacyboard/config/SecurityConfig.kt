package me.kzv.legacyboard.config

import me.kzv.legacyboard.entity.enums.RoleType
import me.kzv.legacyboard.security.AuthenticationProviderImpl
import me.kzv.legacyboard.security.UserDetailsServiceImpl
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val userDetailsService: UserDetailsServiceImpl,
    private val authenticationProvider: AuthenticationProviderImpl
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and().httpBasic().disable()

        http
            .authorizeHttpRequests { authorize ->
                authorize
                    .shouldFilterAllDispatcherTypes(false)
                    .requestMatchers("/", "/signin", "/signup").permitAll()
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .anyRequest().hasAnyRole(RoleType.USER.toString(), RoleType.ADMIN.toString())
            }

        http
            .formLogin()
            .loginPage("/signin")
            .loginProcessingUrl("/signin")
            .defaultSuccessUrl("/", true)

        http
            .userDetailsService(userDetailsService)
            .authenticationProvider(authenticationProvider)

        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers("/vendor/**")
        }
    }
}