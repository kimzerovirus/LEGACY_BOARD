package me.kzv.legacyboard.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class AuthenticationProviderImpl(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider{
    override fun authenticate(authentication: Authentication): Authentication {
        val email = authentication.name
        val password = authentication.credentials as String

        val userDetailsImpl = userDetailsService.loadUserByUsername(email) as UserDetailsImpl
        if (!passwordEncoder.matches(password, userDetailsImpl.password)) {
            throw BadCredentialsException("비밀번호가 일치하지 않음!!!");
        }

        return UsernamePasswordAuthenticationToken(
            userDetailsImpl.member, null,
            userDetailsImpl.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}