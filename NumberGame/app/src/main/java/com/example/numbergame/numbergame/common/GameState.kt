package com.example.numbergame.numbergame.common

data class GameState(
    val targetSum: Int,
    val numbers: List<Int>,
    val selectedNumbers: List<Int> = listOf(),
    val isGameOver: Boolean = false
)