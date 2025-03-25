package com.example.myapplication

import AffirmationsApp
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.practical.ArtSpace
import com.example.myapplication.practical.CalculateTip
import com.example.myapplication.practical.JobTopic
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent {
            MyApplicationTheme {
                JobTopic()
            }
        }
    }
}



