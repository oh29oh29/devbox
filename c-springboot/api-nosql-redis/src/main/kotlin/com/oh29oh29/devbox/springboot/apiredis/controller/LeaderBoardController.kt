package com.oh29oh29.devbox.springboot.apiredis.controller

import com.oh29oh29.devbox.springboot.apiredis.service.LeaderBoardService
import org.springframework.web.bind.annotation.*

@RestController
class LeaderBoardController(
    private val leaderBoardService: LeaderBoardService
) {

    @PostMapping("/score")
    fun score(@RequestBody request: ScoreRequest) {
        leaderBoardService.score(request.name, request.value, request.date)
    }

    data class ScoreRequest(
        val name: String,
        val value: Int,
        val date: String
    )

    @PostMapping("/play")
    fun playGame() {
        leaderBoardService.playGame()
    }

    @GetMapping("/rank")
    fun getRank(@RequestParam("date") date: String): GetRankResponse =
        leaderBoardService.getRank(date)
            .mapIndexed { index, rank ->
                GetRankResponse.Rank(
                    index + 1,
                    rank.name,
                    rank.score
                )
            }
            .let { GetRankResponse(it) }

    @GetMapping("/rank/weekly")
    fun getWeeklyRank(): GetRankResponse =
        leaderBoardService.getWeeklyRank()
            .mapIndexed { index, rank ->
                GetRankResponse.Rank(
                    index + 1,
                    rank.name,
                    rank.score
                )
            }
            .let { GetRankResponse(it) }

    data class GetRankResponse(
        val ranks: List<Rank>
    ) {
        data class Rank(
            val rank: Int,
            val name: String,
            val score: Int
        )
    }
}