package de.pan.mockalender

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import kotlinx.datetime.LocalDateTime
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter

@Configuration
class JacksonConfig {
    @Bean
    fun customLocalDateTimeModule(): Module {
        val module = SimpleModule()
        module.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
        module.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
        return module
    }
}

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun deserialize(parser: JsonParser, ctxt: DeserializationContext): LocalDateTime {
        val dateString = parser.text
        return LocalDateTime.parse(java.time.LocalDateTime.parse(dateString, formatter).toString())
    }
}

class LocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun serialize(value: LocalDateTime, gen: JsonGenerator, serializers: SerializerProvider) {
        val formattedDate = java.time.LocalDateTime.parse(value.toString()).format(formatter)
        gen.writeString(formattedDate)
    }
}