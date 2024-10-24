package com.oh29oh29.devbox.springboot.apiredis.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RedisService(
    private val redisTemplate: StringRedisTemplate
) {
    private final val valueOperations: ValueOperations<String, String> = redisTemplate.opsForValue()

    init {
        initialize()
        setValue()
        getValue()
    }

    private fun initialize() {
        redisTemplate.delete(listOf("a", "b"))
    }

    private fun setValue() {
        valueOperations.set("a", "[ a ] ${LocalDateTime.now()}")
        valueOperations.set("b", "[ b ] ${LocalDateTime.now()}")

        valueOperations.setIfAbsent("a", "[ a-dup ] ${LocalDateTime.now()}")
        valueOperations.setIfAbsent("b", "[ b-dup ] ${LocalDateTime.now()}")

        valueOperations.setIfPresent("a-2", "[ a-2 ] ${LocalDateTime.now()}")
        valueOperations.setIfPresent("b-2", "[ b-2 ] ${LocalDateTime.now()}")
    }

    private fun getValue() {
        println(valueOperations.get("a"))
        println(valueOperations.get("b"))
        println(valueOperations.get("a-2"))
        println(valueOperations.get("b-2"))
    }
}