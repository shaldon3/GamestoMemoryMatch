package com.example.cardsgame

data class MemoryCard(
    val id: Int,
    val content: String,
    var isFlipped: Boolean = false,
    var isMatched: Boolean = false
)
