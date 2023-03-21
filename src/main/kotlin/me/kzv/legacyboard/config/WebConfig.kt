package me.kzv.legacyboard.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // 정적자원 매핑
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/")
    }
}