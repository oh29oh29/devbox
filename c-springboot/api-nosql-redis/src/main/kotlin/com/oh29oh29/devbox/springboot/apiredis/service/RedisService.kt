package com.oh29oh29.devbox.springboot.apiredis.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RedisService(
    redisTemplate: StringRedisTemplate
) {
    private final val ops: ValueOperations<String, String> = redisTemplate.opsForValue()

    init {
        setValue()
        getValue()
    }

    private fun setValue() {
        ops.set("a", "a: ${LocalDateTime.now()}")
        ops.set("b", "b: ${LocalDateTime.now()}")
    }

    private fun getValue() {
        println(ops.get("a"))
        println(ops.get("b"))
    }
}