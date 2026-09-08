package com.example.projectdashboardshowcase.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier.fillMaxSize()
    ) {innerPadding ->
        Column(
            Modifier.padding(innerPadding)
        ) {
            Button(onClick = {}) { Text("Hi")}
        }

    }
}


@Preview
@Composable
fun MainScreenPreview(){
    ProjectDashboardShowcaseTheme {
        MainScreen( )
    }
}