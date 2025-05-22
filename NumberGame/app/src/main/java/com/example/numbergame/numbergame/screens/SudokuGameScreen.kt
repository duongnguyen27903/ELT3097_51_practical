package com.example.numbergame.numbergame.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SudokuGameScreen(
    onNavigateBack: () -> Unit
) {
    var sudokuBoard by remember { mutableStateOf(generateSudokuBoard()) }
    
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var isCompleted by remember { mutableStateOf(false) }
    var isGameOver by remember { mutableStateOf(false) }
    var gameOverMessage by remember { mutableStateOf("") }
    var isSolving by remember { mutableStateOf(false) }

    val solvedSudokuBoard by remember { 
        mutableStateOf(solveSudoku(copyBoard(sudokuBoard)) ?: Array(9) { Array(9) { 0 } }) 
    }
    
    val coroutineScope = rememberCoroutineScope()

    if (isGameOver || isCompleted) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text(text = if (isCompleted) "Chúc mừng!" else "Trò chơi kết thúc") },
            text = { Text(text = if (isCompleted) "Bạn đã hoàn thành Sudoku!" else gameOverMessage) },
            confirmButton = {
                Button(
                    onClick = {
                        sudokuBoard = generateSudokuBoard()
                        selectedCell = null
                        isCompleted = false
                        isGameOver = false
                        gameOverMessage = ""
                        isSolving = false
                    }
                ) {
                    Text("Trò chơi mới")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isGameOver = false
                        isCompleted = false
                    }
                ) {
                    Text("Tiếp tục")
                }
            }
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sudoku",
                fontSize = 24.sp
            )
            
            Button(
                onClick = onNavigateBack
            ) {
                Text("Quay lại")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(9),
                modifier = Modifier.fillMaxSize()
            ) {
                items(81) { index ->
                    val row = index / 9
                    val col = index % 9
                    val value = sudokuBoard[row][col]
                    val isSelected = selectedCell == Pair(row, col)
                    val isFixed = sudokuBoard[row][col] != 0 && sudokuBoard[row][col] == solvedSudokuBoard[row][col]
                    
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .border(
                                width = if (col % 3 == 2 && col < 8) 2.dp else 0.5.dp,
                                color = if (col % 3 == 2 && col < 8) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                            .border(
                                width = if (row % 3 == 2 && row < 8) 2.dp else 0.5.dp,
                                color = if (row % 3 == 2 && row < 8) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                            .background(
                                when {
                                    isSelected -> MaterialTheme.colorScheme.primaryContainer
                                    else -> MaterialTheme.colorScheme.surface
                                }
                            )
                            .clickable(enabled = !isGameOver && !isCompleted && !isSolving) {
                                selectedCell = Pair(row, col)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (value != 0) {
                            Text(
                                text = value.toString(),
                                fontSize = 18.sp,
                                color = if (isFixed) MaterialTheme.colorScheme.primary else Color.Black,
                                fontWeight = if (isFixed) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(9) { number ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                        )
                        .clickable(enabled = !isGameOver && !isCompleted && !isSolving && selectedCell != null) {
                            selectedCell?.let { (row, col) ->
                                val valueToPlace = number + 1
                                val newBoard = copyBoard(sudokuBoard)
                                newBoard[row][col] = valueToPlace
                                sudokuBoard = newBoard

                                if (isSudokuCompleted(sudokuBoard)) {
                                    if (isSudokuCorrect(sudokuBoard, solvedSudokuBoard)) {
                                        isCompleted = true
                                    } else {
                                        isGameOver = true
                                        gameOverMessage = "Bạn đã điền sai! Vui lòng thử lại."
                                    }
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (number + 1).toString(),
                        fontSize = 18.sp
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .background(
                            MaterialTheme.colorScheme.errorContainer,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                        )
                        .clickable(enabled = !isGameOver && !isCompleted && !isSolving && selectedCell != null) {
                            selectedCell?.let { (row, col) ->
                                val newBoard = copyBoard(sudokuBoard)
                                newBoard[row][col] = 0
                                sudokuBoard = newBoard
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "X",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    sudokuBoard = generateSudokuBoard()
                    selectedCell = null
                    isCompleted = false
                    isGameOver = false
                    gameOverMessage = ""
                    isSolving = false
                }
            ) {
                Text("Trò chơi mới")
            }
            
            Button(
                onClick = {
                    selectedCell?.let { (row, col) ->
                        if (sudokuBoard[row][col] == 0) {
                            val correctValue = solvedSudokuBoard[row][col]
                            val newBoard = copyBoard(sudokuBoard)
                            newBoard[row][col] = correctValue
                            sudokuBoard = newBoard

                            if (isSudokuCompleted(sudokuBoard)) {
                                isCompleted = true
                            }
                        }
                    }
                },
                enabled = selectedCell != null && !isGameOver && !isCompleted && !isSolving && 
                         (selectedCell?.let { (row, col) -> sudokuBoard[row][col] == 0 } ?: false)
            ) {
                Text("Gợi ý")
            }
            
            Button(
                onClick = {
                    coroutineScope.launch {
                        isSolving = true
                        val emptyCells = mutableListOf<Pair<Int, Int>>()
                        for (i in 0 until 9) {
                            for (j in 0 until 9) {
                                if (sudokuBoard[i][j] == 0) {
                                    emptyCells.add(Pair(i, j))
                                }
                            }
                        }

                        for (cell in emptyCells) {
                            val (row, col) = cell
                            delay(300)
                            
                            val correctValue = solvedSudokuBoard[row][col]
                            val newBoard = copyBoard(sudokuBoard)
                            newBoard[row][col] = correctValue
                            sudokuBoard = newBoard
                        }

                        isCompleted = true
                        isSolving = false
                    }
                },
                enabled = !isGameOver && !isCompleted && !isSolving && 
                         !isSudokuCompleted(sudokuBoard)
            ) {
                Text("Giải toàn bộ")
            }
        }
    }
}

fun generateSudokuBoard(): Array<Array<Int>> {
    val board = Array(9) { Array(9) { 0 } }

    val solvedBoard = Array(9) { Array(9) { 0 } }

    for (i in 0 until 9 step 3) {
        fillBox(solvedBoard, i, i)
    }

    solveSudoku(solvedBoard)

    for (i in 0 until 9) {
        for (j in 0 until 9) {
            board[i][j] = solvedBoard[i][j]
        }
    }

    val cellsToKeep = 25 + Random.nextInt(6) // 25-30 ô
    val totalCells = 81
    val cellsToRemove = totalCells - cellsToKeep
    
    var count = 0
    while (count < cellsToRemove) {
        val row = Random.nextInt(9)
        val col = Random.nextInt(9)
        
        if (board[row][col] != 0) {
            board[row][col] = 0
            count++
        }
    }
    
    return board
}

fun fillBox(board: Array<Array<Int>>, row: Int, col: Int) {
    val nums = (1..9).toMutableList()
    nums.shuffle()
    
    var numIndex = 0
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            board[row + i][col + j] = nums[numIndex++]
        }
    }
}

fun isValid(board: Array<Array<Int>>, row: Int, col: Int, num: Int): Boolean {
    for (j in 0 until 9) {
        if (board[row][j] == num) return false
    }

    for (i in 0 until 9) {
        if (board[i][col] == num) return false
    }

    val boxRow = row - row % 3
    val boxCol = col - col % 3
    
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (board[boxRow + i][boxCol + j] == num) return false
        }
    }
    
    return true
}

fun solveSudoku(board: Array<Array<Int>>): Array<Array<Int>>? {
    for (row in 0 until 9) {
        for (col in 0 until 9) {
            if (board[row][col] == 0) {
                for (num in 1..9) {
                    if (isValid(board, row, col, num)) {
                        board[row][col] = num
                        
                        if (solveSudoku(board) != null) {
                            return board
                        }
                        
                        board[row][col] = 0
                    }
                }
                return null
            }
        }
    }
    return board
}

fun isSudokuCompleted(board: Array<Array<Int>>): Boolean {
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (board[i][j] == 0) return false
        }
    }
    return true
}

fun isSudokuCorrect(board: Array<Array<Int>>, solvedBoard: Array<Array<Int>>): Boolean {
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (board[i][j] != solvedBoard[i][j]) {
                return false
            }
        }
    }
    return true
}

fun copyBoard(board: Array<Array<Int>>): Array<Array<Int>> {
    return Array(9) { i -> Array(9) { j -> board[i][j] } }
}