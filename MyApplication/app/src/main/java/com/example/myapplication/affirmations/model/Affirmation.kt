package com.example.myapplication.affirmations.model
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)

data class JobTopic(
    @StringRes val stringResourceId: Int,
    val count : Int,
    @DrawableRes val imageResourceId: Int
)