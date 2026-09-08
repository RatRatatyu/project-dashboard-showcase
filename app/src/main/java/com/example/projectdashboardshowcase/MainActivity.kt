package com.example.projectdashboardshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.projectdashboardshowcase.presentation.mainScreen.MainScreen
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectDashboardShowcaseTheme {
                MainScreen()
            }
        }
    }
}
