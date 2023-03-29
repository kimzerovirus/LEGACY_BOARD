package me.kzv.legacyboard.infra.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler

class LoginSuccessHandler(defaultTargetUrl: String) :
    SavedRequestAwareAuthenticationSuccessHandler() { // 요걸로 구현해주면 중단된 위치로 이동

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        super.onAuthenticationSuccess(request, response, authentication)
    }

    init {
        setDefaultTargetUrl(defaultTargetUrl)
    }
}