package com.oh29oh29.devbox.springboot.apiredis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@Configuration
class RedisConfig {

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): StringRedisTemplate {
        val template = StringRedisTemplate()
        template.connectionFactory = connectionFactory
        return template
    }
}