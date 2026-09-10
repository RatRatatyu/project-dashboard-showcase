package com.example.projectdashboardshowcase.presentation.mainScreen.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.abs

@Composable
fun  DefaultUserAvatar(
    modifier: Modifier = Modifier,
    userName: String
) {
    val avatarColors = listOf(
        Color(0xFF1E3A8A),
        Color(0xFF0D9488),
        Color(0xFF10B981),
        Color(0xFF0D4394),
        Color(0xFF5F77B4),)
    val colorIndex = abs(userName.hashCode()) % avatarColors.size
    val backgroundColor = avatarColors[colorIndex]

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = userName.take(1).uppercase(),
            color = Color.White
        )
    }
}