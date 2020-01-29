package ftc.shift.sample

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Конфигурация для отправки / получения сообщений в формате JSON
 */
@Configuration
open class JacksonConfiguration : WebMvcConfigurer {
    override fun extendMessageConverters(converters: List<HttpMessageConverter<*>?>) {
        for (httpConverter in converters) {
            if (httpConverter is MappingJackson2HttpMessageConverter) {
                val mapper = ObjectMapper()
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                httpConverter.objectMapper = mapper
            }
        }
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:static/css/")
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:static/js/")
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:static/fonts/")
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:static/img/")
    }
}