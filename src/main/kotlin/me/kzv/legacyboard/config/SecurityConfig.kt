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
            .anonymous().authorities(RoleType.ANONYMOUS.toString()).and() // https://stackoverflow.com/questions/33327677/java-based-configuration-to-enable-spring-security-anonymous-access
            .authorizeHttpRequests { authorize ->
                authorize
                    .shouldFilterAllDispatcherTypes(false)
                    .requestMatchers("/", "/signin", "/signup", "/board/view/**").permitAll()
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/board/write", "/board/edit/**", "/api/v1/**", "/mypage").hasAnyRole(RoleType.USER.toString(), RoleType.ADMIN.toString())
                    .anyRequest().permitAll()
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
        return WebSecurityCustomizer { it.ignoring().requestMatchers("/resources/**")
        }
    }
}