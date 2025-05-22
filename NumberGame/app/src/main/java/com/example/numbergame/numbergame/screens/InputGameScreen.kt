package com.example.numbergame.numbergame.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun InputGameScreen(
    onNavigateBack: () -> Unit
) {
    var targetSum: Int by remember { mutableStateOf( Random.nextInt(1,1000)) }
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tổng cần tìm: $targetSum",
        )
        
        OutlinedTextField(
            value = input1,
            onValueChange = { input1 = it },
            label = { Text("Player 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = input2,
            onValueChange = { input2 = it },
            label = { Text("Player 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        
        Button(
            onClick = {
                val num1 = input1.toIntOrNull()
                val num2 = input2.toIntOrNull()
                if (num1 != null && num2 != null) {
                    if (num1 + num2 == targetSum) {
                        message = "Chính xác!"
                        targetSum = Random.nextInt(1,1000)
                    } else {
                        message = "Thử lại!"
                    }
                    showDialog = true
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Kiểm tra")
        }
        
        Button(
            onClick = onNavigateBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Quay lại")
        }
    }
    
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Thông báo") },
            text = { Text(message) },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    input1 = ""
                    input2 = ""
                }) {
                    Text("Đóng")
                }
            }
        )
    }
}