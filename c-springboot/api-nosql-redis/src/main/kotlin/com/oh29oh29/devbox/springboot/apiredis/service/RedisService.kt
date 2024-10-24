package com.oh29oh29.devbox.springboot.apiredis.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Service

@Service
class RedisService(
    private val redisTemplate: StringRedisTemplate
) {
    private final val zSetOperations: ZSetOperations<String, String> = redisTemplate.opsForZSet()

    init {
        initialize()
        setValue()
        getValue()
    }

    private fun initialize() {
        redisTemplate.delete(listOf("main:20240101"))
    }

    private fun setValue() {
        zSetOperations.add("main:20240101", "sub:a", 100.0)
        zSetOperations.add("main:20240101", "sub:b", 50.0)
        zSetOperations.add("main:20240101", "sub:c", 200.0)
        zSetOperations.add("main:20240101", "sub:d", 150.0)

        zSetOperations.incrementScore("main:20240101", "sub:d", 1.0)
    }

    private fun getValue() {
        println(zSetOperations.range("main:20240101", 0L, -1L))
        println(zSetOperations.rangeWithScores("main:20240101", 0L, -1L))
        println(zSetOperations.reverseRange("main:20240101", 0L, -1L))
        println(zSetOperations.reverseRangeWithScores("main:20240101", 0L, -1L))
        println()
        println(zSetOperations.score("main:20240101", "sub:a")!!)
        println(zSetOperations.score("main:20240101", "sub:b")!!)
        println(zSetOperations.score("main:20240101", "sub:c")!!)
        println(zSetOperations.score("main:20240101", "sub:d")!!)
        println()
        println(zSetOperations.rank("main:20240101", "sub:a")!!)
        println(zSetOperations.rank("main:20240101", "sub:b")!!)
        println(zSetOperations.rank("main:20240101", "sub:c")!!)
        println(zSetOperations.rank("main:20240101", "sub:d")!!)
    }
}