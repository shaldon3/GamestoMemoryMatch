package com.example.cardsgame

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardsgame.ui.theme.CardsGameTheme
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database // or database.ktx.*
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
        Log.d("MainActivity", "onCreate called")

    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
        Log.d("Firebase", "App name: ${FirebaseApp.getInstance().name}")

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "App name: ${FirebaseApp.getInstance().name}",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardsGameTheme {
        Greeting("Android")
    }
}