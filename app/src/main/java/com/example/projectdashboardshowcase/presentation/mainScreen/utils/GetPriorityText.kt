package com.example.projectdashboardshowcase.presentation.mainScreen.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority


@Composable
fun GetPriorityText(
    priority: TaskPriority
) {
    Text(
        text = when(priority){
            TaskPriority.P1 -> "Высокий приоритет"
            TaskPriority.P2 -> "Средний приоритет"
            TaskPriority.P3 -> "Низкий приоритет"
        }
    )
}