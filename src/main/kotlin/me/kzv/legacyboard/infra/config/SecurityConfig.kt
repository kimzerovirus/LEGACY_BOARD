package me.kzv.legacyboard.infra.config

import me.kzv.legacyboard.member.RoleType
import me.kzv.legacyboard.infra.security.AuthenticationProviderImpl
import me.kzv.legacyboard.infra.security.LoginSuccessHandler
import me.kzv.legacyboard.infra.security.UserDetailsServiceImpl
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
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
                    .requestMatchers("/", "/signin", "/signup", "/search/**", "/board/view/**").permitAll()
//                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/board/write", "/board/edit/**", "/api/v1/**", "/mypage").hasAnyRole(RoleType.USER.toString(), RoleType.ADMIN.toString())
                    .anyRequest().permitAll()
            }

        http
            .formLogin()
            .loginPage("/signin")
            .loginProcessingUrl("/signin")
//            .defaultSuccessUrl("/", false) // https://www.inflearn.com/questions/193737/defaultsuccessurl-%EC%9E%91%EB%8F%99-%EC%88%9C%EC%84%9C
            .successHandler(LoginSuccessHandler("/"))

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