package com.example.myapplication.practical

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.affirmations.data.Jobs
import com.example.myapplication.affirmations.model.JobTopic
import kotlin.math.round

@Preview(showBackground = true)
@Composable
fun JobTopic(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),

    ) {
        items(Jobs().loadTopics() ){ item ->
            JobCard(item)
        }
    }
}

@Composable
fun JobCard(job : JobTopic){
    Row (
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .background(color = Color.Yellow)

    ){
        Image(
            painter = painterResource(job.imageResourceId),
            contentDescription = job.stringResourceId.toString(),
            modifier = Modifier.clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight().fillMaxWidth()
        ) {
            Text(
                text = stringResource(job.stringResourceId),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = job.count.toString()
            )
        }
    }
}