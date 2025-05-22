package com.example.numbergame.numbergame.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.numbergame.components.NumberCell
import com.example.numbergame.utils.generateRandomNumbers
import kotlin.random.Random

@Composable
fun GridGameScreen(
    targetSum: Int = Random.nextInt(1, 50),
    onNavigateBack: () -> Unit
) {
    var numbers by remember { mutableStateOf(generateRandomNumbers(20,targetSum)) }
    var selectedNumbers by remember { mutableStateOf(listOf<Int>()) }
    var selectedIndexes by remember { mutableStateOf(listOf<Int>()) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tổng cần tìm: $targetSum",
        )
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(numbers.size) { index ->
                NumberCell(
                    number = numbers[index],
                    isSelected = index in selectedIndexes,
                    onClick = {
                        val currentNumber = numbers[index]
                        if (index !in selectedIndexes) {
                            val newSelected = selectedNumbers + currentNumber
                            val newSelectedIndexes = selectedIndexes + index
                            if (newSelected.size == 2) {
                                if (newSelected.sum() == targetSum) {
                                    numbers = numbers.filterIndexed { i, _ ->
                                        i !in newSelectedIndexes
                                    }
                                }
                                selectedNumbers = listOf()
                                selectedIndexes = listOf()
                            } else {
                                selectedNumbers = newSelected
                                selectedIndexes = newSelectedIndexes
                            }
                        }
                    }
                )
            }
        }

        Button(
            onClick = onNavigateBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Quay lại")
        }
    }
}