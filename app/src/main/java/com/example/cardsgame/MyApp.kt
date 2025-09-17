package com.example.cardsgame

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "username") {
        composable("username") {
            UsernameScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }

        composable("game") {
            GameScreen(navController = navController, onRestart = {
                navController.popBackStack()
                navController.navigate("game")
            })
        }


    }
}
