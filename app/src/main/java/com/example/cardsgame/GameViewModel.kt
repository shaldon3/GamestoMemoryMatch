package com.example.cardsgame

import android.content.Context.MODE_PRIVATE
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext
import kotlin.random.Random

class GameViewModel : ViewModel() {
    var cards = mutableStateListOf<MemoryCard>()
    var matchedCount = mutableStateOf(0)
    var timer = mutableStateOf(0)
    var gameWon = mutableStateOf(false)

    private var flippedCards = mutableListOf<MemoryCard>()
    private var timerStarted = false

    init {
        restartGame()
    }

    fun restartGame() {
        val emojis = listOf("🍎", "🍌", "🍇", "🍓", "🍍", "🍒", "🥝", "🍉")
        val pairList = (emojis + emojis).shuffled()
        cards.clear()
        cards.addAll(pairList.mapIndexed { index, emoji -> MemoryCard(index, emoji) })
        matchedCount.value = 0
        flippedCards.clear()
        gameWon.value = false
        timer.value = 0
        timerStarted = false
    }

    fun flipCard(card: MemoryCard) {
        if (card.isFlipped || card.isMatched || flippedCards.size == 2) return

        if (!timerStarted) {
            timerStarted = true
            startTimer()
        }

        card.isFlipped = true
        flippedCards.add(card)

        if (flippedCards.size == 2) {
            viewModelScope.launch {
                delay(500)
                val (first, second) = flippedCards
                if (first.content == second.content) {
                    first.isMatched = true
                    second.isMatched = true
                    matchedCount.value += 2
                    if (matchedCount.value == 16) {
                        gameWon.value = true

                    }
                } else {
                    first.isFlipped = false
                    second.isFlipped = false
                }
                flippedCards.clear()
            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (!gameWon.value) {
                delay(1000)
                timer.value++
            }
        }
    }

    fun saveScoreToFirebase(context: Context, seconds: Int) {
        val score = calculateScore()
        val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Player") ?: "Player"
        val ref = FirebaseDatabase.getInstance().getReference("leaderboard")
        val entry = mapOf("username" to username, "score" to score)
        ref.push().setValue(entry)
    }

    fun calculateScore(): Int {

        val time = timer.value
        return if (gameWon.value) {
            maxOf(0, 1000 - (time * 5)) // Prevent negative scores
        } else {
            0
        }
    }

}
