package com.example.cardsgame

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CardView(card: MemoryCard, onClick: () -> Unit) {
    val rotation = animateFloatAsState(targetValue = if (card.isFlipped) 180f else 0f)

    Box(
        modifier = Modifier
            .size(70.dp)
            .graphicsLayer { rotationY = rotation.value }
            .clickable(enabled = !card.isMatched) { onClick() }
            .background(
                if (card.isFlipped || card.isMatched) Color.White else Color(0xFF90CAF9),
                shape = RoundedCornerShape(8.dp)
            )
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (card.isFlipped || card.isMatched) {
            Text(text = card.content, fontSize = 24.sp)
        } else {
            Text(text = "❓", fontSize = 24.sp)
        }
    }
}
