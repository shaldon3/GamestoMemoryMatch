package com.example.cardsgame

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0)) // light pastel peach background
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome!",
            fontSize = 32.sp,
            color = Color(0xFF2E4057), // dark pastel blue-gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Enter your username",
            fontSize = 18.sp,
            color = Color(0xFF2E4057)
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text("Enter your Username") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF95E1D3),
                unfocusedIndicatorColor = Color(0xFFCCCCCC),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray
            )
        )


        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.padding(10.dp),
            onClick = {
                if (username.isNotBlank()) {
                    saveUsernameToPrefs(context, username)
                    navController.navigate("home")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF38181)) // soft coral
        ) {
            Text("Continue", color = Color.White, modifier = Modifier.padding(7.dp))
        }
    }
}

fun saveUsernameToPrefs(context: Context, username: String) {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    prefs.edit().putString("username", username).apply()
}
