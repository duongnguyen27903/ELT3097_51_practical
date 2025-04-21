package com.example.bookshelf.layout.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable

@Composable
fun BookShelfApp(){
    LazyVerticalGrid(
        columns = new GridCells(2)
    ) { }
}