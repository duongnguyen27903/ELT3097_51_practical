package com.example.numbergame.utils

import kotlin.random.Random

fun generateRandomNumbers(size: Int = 16, maxNumber: Int = 20): List<Int> {
    // Đảm bảo size là số chẵn
    val adjustedSize = if (size % 2 != 0) size + 1 else size
    
    // Tạo danh sách các cặp số có tổng bằng maxNumber
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in 1 until maxNumber) {
        val complement = maxNumber - i
        if (i <= complement) { // Tránh trùng lặp cặp số
            pairs.add(Pair(i, complement))
        }
    }
    
    // Đảm bảo có đủ cặp số
    val requiredPairs = adjustedSize / 2
    val selectedPairs = if (pairs.size >= requiredPairs) {
        pairs.shuffled().take(requiredPairs)
    } else {
        // Nếu không đủ cặp số, lặp lại các cặp đã có
        val repeatedPairs = mutableListOf<Pair<Int, Int>>()
        repeat(requiredPairs) {
            repeatedPairs.add(pairs[it % pairs.size])
        }
        repeatedPairs
    }
    
    // Tạo danh sách số từ các cặp đã chọn
    val numbers = mutableListOf<Int>()
    for (pair in selectedPairs) {
        numbers.add(pair.first)
        numbers.add(pair.second)
    }
    
    // Trả về danh sách đã xáo trộn
    return numbers.shuffled()
}