package com.example.numbergame.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random
import java.util.LinkedList
import java.util.Queue

class NumberConnectGame(val m: Int, val n: Int, val targetSum: Int) {
    var board: Array<Array<Int?>> = Array(m) { Array<Int?>(n) { null } }
    
    init {
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    board[i][j] = null
                } else {
                    board[i][j] = 0
                }
            }
        }
        fillBoardWithPairs()
    }

    private fun fillBoardWithPairs() {
        val positions = mutableListOf<Pair<Int, Int>>()
        for (i in 1 until m - 1) {
            for (j in 1 until n - 1) {
                positions.add(Pair(i, j))
            }
        }
        positions.shuffle()
        while (positions.size >= 2) {
            val pos1 = positions.removeAt(0)
            val pos2 = positions.removeAt(0)
            val num1 = Random.nextInt(1, targetSum)
            val num2 = targetSum - num1
            board[pos1.first][pos1.second] = num1
            board[pos2.first][pos2.second] = num2
        }
        if (positions.isNotEmpty()) {
            val pos = positions.removeAt(0)
            board[pos.first][pos.second] = 0
        }
    }

    fun canConnect(x1: Int, y1: Int, x2: Int, y2: Int): List<Pair<Int, Int>>? {
        if (!isValidCell(x1, y1) || !isValidCell(x2, y2)) return null
        if (board[x1][y1] == null || board[x2][y2] == null) return null
        if (x1 == x2 && y1 == y2) return null
        if ((board[x1][y1] ?: 0) + (board[x2][y2] ?: 0) != targetSum) return null

        val visited = Array(m) { BooleanArray(n) { false } }
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val parent = Array(m) { Array<Pair<Int, Int>?>(n) { null } }
        
        queue.add(Pair(x1, y1))
        visited[x1][y1] = true

        val dx = arrayOf(0, 1, 0, -1)
        val dy = arrayOf(1, 0, -1, 0)

        while (queue.isNotEmpty()) {
            val (cx, cy) = queue.poll()
            for (dir in 0..3) {
                val nx = cx + dx[dir]
                val ny = cy + dy[dir]
                if (nx in 0 until m && ny in 0 until n && !visited[nx][ny]) {
                    if ((nx == x2 && ny == y2) ||
                        board[nx][ny] == 0 || board[nx][ny] == null) {
                        visited[nx][ny] = true
                        parent[nx][ny] = Pair(cx, cy)
                        queue.add(Pair(nx, ny))
                        
                        if (nx == x2 && ny == y2) {
                            val path = mutableListOf<Pair<Int, Int>>()
                            var current = Pair(nx, ny)
                            while (current != Pair(x1, y1)) {
                                path.add(current)
                                current = parent[current.first][current.second]!!
                            }
                            path.add(Pair(x1, y1))
                            path.reverse()

                            if (path.size >= 4) {
                                var directionChanges = 0
                                var lastDirection = -1
                                
                                for (i in 0 until path.size - 1) {
                                    val currentDirection = getDirection(path[i], path[i + 1])
                                    if (lastDirection != -1 && currentDirection != lastDirection) {
                                        directionChanges++
                                    }
                                    lastDirection = currentDirection

                                    if (directionChanges > 3) {
                                        return null
                                    }
                                }
                            }
                            
                            return path
                        }
                    }
                }
            }
        }
        return null
    }

    private fun getDirection(from: Pair<Int, Int>, to: Pair<Int, Int>): Int {
        val dx = to.first - from.first
        val dy = to.second - from.second
        
        return when {
            dx > 0 -> 1
            dx < 0 -> 3
            dy > 0 -> 0
            else -> 2
        }
    }

    fun connectCells(x1: Int, y1: Int, x2: Int, y2: Int): List<Pair<Int, Int>>? {
        val path = canConnect(x1, y1, x2, y2)
        if (path != null) {
            board[x1][y1] = null
            board[x2][y2] = null
            return path
        }
        return null
    }

    private fun isValidCell(x: Int, y: Int): Boolean {
        return x in 0 until m && y in 0 until n
    }

    fun hasValidPairs(): Boolean {
        val cells = mutableListOf<Pair<Int, Int>>()
        for (i in 1 until m - 1) {
            for (j in 1 until n - 1) {
                if (board[i][j] != null && board[i][j] != 0) {
                    cells.add(Pair(i, j))
                }
            }
        }
        
        for (i in cells.indices) {
            for (j in i + 1 until cells.size) {
                val (x1, y1) = cells[i]
                val (x2, y2) = cells[j]
                if ((board[x1][y1] ?: 0) + (board[x2][y2] ?: 0) == targetSum) {
                    if (canConnect(x1, y1, x2, y2) != null) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun isGameOver(): Boolean {
        for (i in 1 until m - 1) {
            for (j in 1 until n - 1) {
                if (board[i][j] != null && board[i][j] != 0) {
                    return false
                }
            }
        }
        return true
    }
}

@Composable
fun PikachuNumberConnect(
    onNavigateBack: () -> Unit = {}
) {
    val rows = 8
    val cols = 8
    val targetSum = 10
    
    var game by remember { mutableStateOf(NumberConnectGame(rows, cols, targetSum)) }
    var selectedCells by remember { mutableStateOf(listOf<Pair<Int, Int>>()) }
    var message by remember { mutableStateOf("") }
    var connectionPath by remember { mutableStateOf<List<Pair<Int, Int>>?>(null) }
    var showPath by remember { mutableStateOf(false) }
    var gameOver by remember { mutableStateOf(false) }
    var cellSize by remember { mutableStateOf(40.dp) }

    var animationProgress by remember { mutableStateOf(0f) }
    var isAnimating by remember { mutableStateOf(false) }
    
    LaunchedEffect(game) {
        gameOver = game.isGameOver()
        if (gameOver) {
            message = "Chúc mừng! Bạn đã hoàn thành trò chơi!"
        } else if (!game.hasValidPairs()) {
            message = "Không còn cặp số nào có thể nối được. Hãy chơi lại!"
        }
    }

    LaunchedEffect(showPath) {
        if (showPath && connectionPath != null) {
            isAnimating = true
            animationProgress = 0f
            val animationDuration = 500
            val startTime = System.currentTimeMillis()
            while (isAnimating) {
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - startTime
                if (elapsedTime >= animationDuration) {
                    animationProgress = 1f
                    isAnimating = false
                } else {
                    animationProgress = elapsedTime.toFloat() / animationDuration
                }
                delay(16)
            }

            delay(500)

            showPath = false
            connectionPath = null
            selectedCells = listOf()

            gameOver = game.isGameOver()
            if (gameOver) {
                message = "Chúc mừng! Bạn đã hoàn thành trò chơi!"
            } else if (!game.hasValidPairs()) {
                message = "Không còn cặp số nào có thể nối được. Hãy chơi lại!"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Pikachu Number Connect",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            "Tìm và nối các cặp số có tổng bằng $targetSum",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        if (message.isNotEmpty()) {
            Text(
                message,
                color = if (gameOver) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.LightGray.copy(alpha = 0.3f))
        ) {
            // Vẽ bảng game
            Column {
                for (i in 1 until rows - 1) {
                    Row {
                        for (j in 1 until cols - 1) {
                            val value = game.board[i][j]
                            val isSelected = selectedCells.contains(Pair(i, j))
                            
                            Box(
                                modifier = Modifier
                                    .size(cellSize)
                                    .padding(2.dp)
                                    .background(
                                        when {
                                            isSelected -> Color.Yellow
                                            value == null -> Color.LightGray.copy(alpha = 0.5f)
                                            value == 0 -> Color.LightGray.copy(alpha = 0.5f)
                                            else -> Color.White
                                        }
                                    )
                                    .border(1.dp, Color.Gray)
                                    .clickable(
                                        enabled = value != null && value != 0 && !showPath && !gameOver && !isAnimating
                                    ) {
                                        if (selectedCells.isEmpty()) {
                                            selectedCells = listOf(Pair(i, j))
                                        } else if (selectedCells.size == 1) {
                                            val (x1, y1) = selectedCells[0]
                                            if (x1 == i && y1 == j) {
                                                selectedCells = listOf()
                                            } else {
                                                val path = game.connectCells(x1, y1, i, j)
                                                if (path != null) {
                                                    connectionPath = path
                                                    showPath = true
                                                    message = "Nối thành công!"
                                                } else {
                                                    message = "Không thể nối hai ô này!"
                                                    selectedCells = listOf(Pair(i, j))
                                                }
                                            }
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (value != null && value != 0) {
                                    Text(
                                        text = value.toString(),
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (showPath && connectionPath != null) {
                Canvas(
                    modifier = Modifier.matchParentSize()
                ) {
                    val path = connectionPath!!
                    val segmentsToShow = (path.size * animationProgress).toInt().coerceAtMost(path.size - 1)
                    
                    for (i in 0 until segmentsToShow) {
                        val (x1, y1) = path[i]
                        val (x2, y2) = path[i + 1]

                        // Chuyển đổi tọa độ lưới sang tọa độ canvas
                        val startX = (y1 - 0.5f) * cellSize.toPx()
                        val startY = (x1 - 0.5f) * cellSize.toPx()
                        val endX = (y2 - 0.5f) * cellSize.toPx()
                        val endY = (x2 - 0.5f) * cellSize.toPx()
                        
                        drawLine(
                            color = Color.Red,
                            start = Offset(startX, startY),
                            end = Offset(endX, endY),
                            strokeWidth = 5f,
                            cap = StrokeCap.Round
                        )
                    }

                    if (segmentsToShow < path.size - 1) {
                        val i = segmentsToShow
                        val (x1, y1) = path[i]
                        val (x2, y2) = path[i + 1]
                        
                        val startX = (y1 - 0.5f) * cellSize.toPx()
                        val startY = (x1 - 0.5f) * cellSize.toPx()
                        val endX = (y2 - 0.5f) * cellSize.toPx()
                        val endY = (x2 - 0.5f) * cellSize.toPx()
                        
                        val progress = animationProgress * path.size - i
                        
                        val currentEndX = startX + (endX - startX) * progress
                        val currentEndY = startY + (endY - startY) * progress
                        
                        drawLine(
                            color = Color.Red,
                            start = Offset(startX, startY),
                            end = Offset(currentEndX, currentEndY),
                            strokeWidth = 5f,
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    game = NumberConnectGame(rows, cols, targetSum)
                    selectedCells = listOf()
                    connectionPath = null
                    showPath = false
                    message = ""
                    gameOver = false
                    isAnimating = false
                }
            ) {
                Text("Chơi mới")
            }
            
            Button(
                onClick = onNavigateBack
            ) {
                Text("Quay lại")
            }
        }
    }
}