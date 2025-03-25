package com.example.myapplication.practical

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Preview(showBackground = true)
@Composable
fun ArtSpace(){

    var currentStep by remember { mutableIntStateOf(1) }
    val (imageResource,stringRes)  = when (currentStep) {
        1-> Pair(R.drawable.lemon_tree,R.string.lemon_tree)

        2-> {
            Pair(R.drawable.lemon_squeeze,R.string.lemon_squeeze)
        }
        3 -> {
            Pair(R.drawable.lemon_drink,R.string.lemon_drink)
        }
        4 -> Pair(R.drawable.lemon_restart,R.string.lemon_restart)
        else -> {
            currentStep = 1
            Pair(R.drawable.lemon_tree,R.string.lemon_tree)
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = "Image",
            modifier = Modifier.fillMaxHeight(0.5f)
                .fillMaxWidth(1f)
                .padding(top = 10.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(stringRes),
                fontSize = 36.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Button(
                onClick = {currentStep--},
                modifier = Modifier.width(100.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Before"
                )
            }
            Button(
                onClick = {currentStep++},
                modifier = Modifier.width(100.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Before"
                )
            }
        }
    }
}