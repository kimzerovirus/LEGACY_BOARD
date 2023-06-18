package me.kzv.legacyboard.infra.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    @Value("\${imgPath}")
    lateinit var imgPath: String
    @Value("\${cors.url}")
    lateinit var corsUrl: String

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // 정적자원 매핑
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/images/**").addResourceLocations("file://$imgPath")
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("http://localhost:8080", corsUrl)
            .allowedMethods(
                GET.name(), POST.name(), PUT.name(), PATCH.name(),
                DELETE.name(), OPTIONS.name(), HEAD.name()
            )
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600)
    }
}