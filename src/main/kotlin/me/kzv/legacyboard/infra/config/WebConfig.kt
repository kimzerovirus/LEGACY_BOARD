package me.kzv.legacyboard.infra.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    @Value("\${uploadPath}")
    lateinit var uploadPath: String

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // 정적자원 매핑
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/images/**").addResourceLocations("file://$uploadPath");
    }
}