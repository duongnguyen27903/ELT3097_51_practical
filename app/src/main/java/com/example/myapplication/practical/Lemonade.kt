package com.example.myapplication.practical

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Preview(showBackground = true)
@Composable
fun Lemonade() {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }
    val (imageResource,stringRes)  = when (currentStep) {
        1-> Pair(R.drawable.lemon_tree,R.string.lemon_tree)

        2-> {
            squeezeCount = (0..2).random()
            println(squeezeCount)
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
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.2f)
                .background(color = Color.Yellow)
                .wrapContentSize(Alignment.Center)
        ){
            Text(
                text = "Lemonade",
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight(0.5f)
        ){
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDCE775)),
                onClick = {}
            ) {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = stringResource(stringRes),
                    modifier = Modifier.clickable {
                        if( currentStep == 2 ){
                            if( squeezeCount == 0)
                                currentStep++
                            else squeezeCount--
                        }
                        else currentStep++
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(stringRes),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}