package com.example.numbergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.numbergame.numbergame.screens.GridGameScreen
import com.example.numbergame.numbergame.screens.InputGameScreen
import com.example.numbergame.numbergame.screens.RunningNumbersScreen
import com.example.numbergame.numbergame.screens.SudokuGameScreen

import com.example.reply.ui.theme.NumberGameTheme
import com.example.numbergame.screens.PikachuNumberConnect


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGameTheme {
                var currentScreen by remember { mutableStateOf("menu") }
                
                when (currentScreen) {
                    "menu" -> MainMenu(
                        onNavigateToScreen = { screen -> currentScreen = screen }
                    )
                    "grid" -> GridGameScreen (
                        onNavigateBack = { currentScreen = "menu" }
                    )
                    "running" -> RunningNumbersScreen(
                        onNavigateBack = { currentScreen = "menu" }
                    )
                    "input" -> InputGameScreen(
                        onNavigateBack = { currentScreen = "menu" }
                    )

                    "pikachu" -> PikachuNumberConnect(
                        onNavigateBack = { currentScreen = "menu" }
                    )

                    "sudoku" -> SudokuGameScreen(
                        onNavigateBack = { currentScreen = "menu" }
                    )
                }
            }
        }
    }
}

@Composable
fun MainMenu(onNavigateToScreen: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Number Game",
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(onClick = { onNavigateToScreen("grid") }) {
            Text("Chế độ lưới số")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = { onNavigateToScreen("running") }) {
            Text("Chế độ số chạy")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = { onNavigateToScreen("input") }) {
            Text("Chế độ nhập số")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = { onNavigateToScreen("sudoku") }) {
            Text("Chế độ Sudoku")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = { onNavigateToScreen("pikachu") }) {
            Text("Pikachu Connect Game")
        }
    }
}

