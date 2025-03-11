package com.example.myapplication.practical

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun BusinessCard (fullName: String,title: String) {
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Identification(fullName,title)
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Contact(icon = Icons.Default.Call, text = "+1234567890")
            Contact(icon = Icons.Default.Share, text = "@duongdev")
            Contact(icon = Icons.Default.Email, text = "duong@gmail.com")
        }
    }
}

@Composable
fun Identification(fullName : String, title : String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(0.8f)
    ) {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .background(color = Color(0xff073042))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = fullName,
            fontSize = 28.sp,
            fontWeight = FontWeight.W300
        )
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF388E3C),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Contact(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.padding(8.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription  = null,
            tint = Color.Green
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp, textAlign = TextAlign.Start)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        BusinessCard(
            "Nguyen Tung Duong",
            "Android Developer Extraordinaire"
        )
    }
}