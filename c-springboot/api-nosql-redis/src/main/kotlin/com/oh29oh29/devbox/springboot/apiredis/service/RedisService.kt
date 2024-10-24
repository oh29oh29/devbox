package com.oh29oh29.devbox.springboot.apiredis.service

import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RedisService(
    private val redisTemplate: StringRedisTemplate
) {
    private final val hashOperations: HashOperations<String, String, String> = redisTemplate.opsForHash()

    init {
        initialize()
        setValue()
        getValue()
    }

    private fun initialize() {
        redisTemplate.delete(listOf("c", "d"))
    }

    private fun setValue() {
        hashOperations.put("c", "c-hk", "[ c-hk ] ${LocalDateTime.now()}")
        hashOperations.put("d", "d-hk", "[ d-hk ] ${LocalDateTime.now()}")

        hashOperations.put("c", "c-hk", "[ c-hk-dup ] ${LocalDateTime.now()}")
        hashOperations.put("d", "d-hk", "[ d-hk-dup ] ${LocalDateTime.now()}")

        hashOperations.putIfAbsent("c", "c-hk", "[ c-hk-new ] ${LocalDateTime.now()}")
        hashOperations.putIfAbsent("d", "d-hk", "[ d-hk-new ] ${LocalDateTime.now()}")

        hashOperations.putIfAbsent("c", "c-hk-2", "[ e-hk-2 ] ${LocalDateTime.now()}")
        hashOperations.putIfAbsent("d", "f-hk-2", "[ f-hk-2 ] ${LocalDateTime.now()}")
    }

    private fun getValue() {
        println(hashOperations.get("c", "c-hk"))
        println(hashOperations.get("d", "d-hk"))
    }
}