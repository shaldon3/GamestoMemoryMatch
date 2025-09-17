package com.example.cardsgame

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val username = remember {
        context.getSharedPreferences("app_prefs", 0).getString("username", "Player") ?: "Player"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF1F1)) // soft pastel pink
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Flip & Match",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6D6875)
        )

        Image(
            painter = painterResource(id = R.drawable.cherries),
            contentDescription = "Decorative image",
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
        )
        Text(
            text = "Welcome, $username!",
            fontSize = 20.sp,
            color = Color(0xFF6D6875)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { navController.navigate("game") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF70C1B3)), // dark pastel green
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Start Game", color = Color.White)
            }

            Button(
                onClick = {
                    val intent = Intent(context, LeaderboardActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6A6B2)), // dark pastel peach
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Leaderboard", color = Color.White)
            }

            Button(
                onClick = {
                    (context as? Activity)?.finish()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE29578)), // dark pastel coral
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Exit", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
