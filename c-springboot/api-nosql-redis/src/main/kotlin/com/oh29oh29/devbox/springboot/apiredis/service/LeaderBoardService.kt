package com.oh29oh29.devbox.springboot.apiredis.service

import com.oh29oh29.devbox.springboot.apiredis.value.Player
import org.springframework.data.redis.connection.zset.Aggregate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.random.Random

@Service
class LeaderBoardService(
    redisTemplate: StringRedisTemplate
) {
    private final val zSetOperations: ZSetOperations<String, String> = redisTemplate.opsForZSet()
    private final val prefix = "player"
    private final val now = LocalDate.now()
    private final val keys = listOf(
        "$prefix:${now.minusDays(7)}",
        "$prefix:${now.minusDays(6)}",
        "$prefix:${now.minusDays(5)}",
        "$prefix:${now.minusDays(4)}",
        "$prefix:${now.minusDays(3)}",
        "$prefix:${now.minusDays(2)}",
        "$prefix:${now.minusDays(1)}",
        "$prefix:${now}"
    )

    fun score(
        name: String,
        value: Int,
        date: String
    ) {
        zSetOperations.incrementScore(getKey(date), Player.of(name).name, value.toDouble())
    }

    fun playGame() {
        Player.entries.forEach {
            zSetOperations.incrementScore(keys[Random.nextInt(0, 7)], it.name, Random.nextInt(0, 3).toDouble())
        }
    }

    fun getRank(date: String): List<Rank> =
        zSetOperations.reverseRangeWithScores(getKey(date), 0, -1)!!
            .map { Rank(it.value!!, it.score!!.toInt()) }

    fun getWeeklyRank(): List<Rank> =
        zSetOperations.unionWithScores("$prefix:weekly:${now}", keys, Aggregate.SUM)!!
            .reversed()
            .map { Rank(it.value!!, it.score!!.toInt()) }

    private fun getKey(date: String) = "$prefix:$date"

    data class Rank(
        val name: String,
        val score: Int
    )
}