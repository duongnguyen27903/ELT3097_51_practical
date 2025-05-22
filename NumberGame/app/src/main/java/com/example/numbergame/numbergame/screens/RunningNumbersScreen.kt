package com.example.numbergame.numbergame.screens

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.example.numbergame.components.NumberCell
import com.example.numbergame.utils.generateRandomNumbers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun RunningNumbersScreen(
    onNavigateBack: () -> Unit
) {
    var targetSum : Int by remember { mutableStateOf(Random.nextInt(1,20)) }
    var numbers1 by remember { mutableStateOf(generateRandomNumbers(10,targetSum)) }
    var numbers2 by remember { mutableStateOf(generateRandomNumbers(10,targetSum)) }
    var selectedTopIndex by remember { mutableStateOf<Int?>(null) }
    var selectedBottomIndex by remember { mutableStateOf<Int?>(null) }

    fun pick(index: Int, number: Int, isTopRow: Boolean) {
        if (isTopRow) {
            selectedTopIndex = index
            selectedBottomIndex?.let { bottomIdx ->
                if (numbers1[index] + numbers2[bottomIdx] == targetSum) {
                    numbers1 = numbers1.toMutableList().also { list ->
                        list.removeAt(index)
                    }
                    numbers2 = numbers2.toMutableList().also { list ->
                        list.removeAt(bottomIdx)
                    }
                    selectedTopIndex = null
                    selectedBottomIndex = null
                }
            }
        } else {
            selectedBottomIndex = index
            selectedTopIndex?.let { topIdx ->
                if (numbers2[index] + numbers1[topIdx] == targetSum) {
                    numbers1 = numbers1.toMutableList().also { list ->
                        list.removeAt(topIdx)
                    }
                    numbers2 = numbers2.toMutableList().also { list ->
                        list.removeAt(index)
                    }
                    selectedTopIndex = null
                    selectedBottomIndex = null
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tổng cần tìm: $targetSum",
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clipToBounds()
        ) {
            Row {
                RunArray(
                    numbers = numbers1,
                    selectedIndex = selectedTopIndex,
                    onclick = { index, number -> pick(index, number, true) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clipToBounds()
        ) {
            Row {
                RunArray(
                    numbers = numbers2,
                    selectedIndex = selectedBottomIndex,
                    onclick = { index, number -> pick(index, number, false) }
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

@Composable
fun RunArray(
    numbers: List<Int>,
    selectedIndex: Int?,
    onclick: (Int, Int) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            val scrolled = listState.scrollBy(2f)
            if (scrolled == 0f) {
                listState.scrollToItem(0)
            }
            delay(100L)
            coroutineScope.launch {
                listState.scrollBy(2f)
            }
        }
    }

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        items(numbers.size) { index ->
            NumberCell(
                number = numbers[index],
                isSelected = index == selectedIndex,
                onClick = { onclick(index, numbers[index]) }
            )
        }
    }
}
