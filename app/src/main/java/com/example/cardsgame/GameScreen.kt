package com.example.cardsgame
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel = viewModel(),
    onRestart: () -> Unit
)
 {
    val cards = viewModel.cards
    val timer = viewModel.timer.value
    val gameWon = viewModel.gameWon.value
    var context= LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF1F1))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onRestart,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF70C1B3)), // dark pastel green
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.Start)
//                    .padding(start = 16.dp)
            ) {
                Text("Restart", color = Color.White)
            }
            Text("Time: ${"%02d:%02d".format(timer / 60, timer % 60)}", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Flip & Match", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cards.size) { index ->
                val card = cards[index]
                CardView(card = card, onClick = { viewModel.flipCard(card) })
            }
        }

        if (gameWon) {
            val score = viewModel.calculateScore()
            // Call Firebase saving logic when game is won
            LaunchedEffect(Unit) {
                viewModel.saveScoreToFirebase(context, timer)
            }
            Text(
                "🎉 YOU WON!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50),
                //modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                "Score: $score",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Button(
                    onClick = onRestart,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8EABC1), // soft dark pastel blue
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Play Again")
                }

                Button(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("game") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB294A4), // soft dark pastel pink/mauve
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Back to Home")
                }

            }

        }
    }
}
