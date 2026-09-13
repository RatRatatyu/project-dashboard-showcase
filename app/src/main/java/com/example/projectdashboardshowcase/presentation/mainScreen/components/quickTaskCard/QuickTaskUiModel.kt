package com.example.projectdashboardshowcase.presentation.mainScreen.components.quickTaskCard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.QuickTask
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority
import com.example.projectdashboardshowcase.presentation.mainScreen.utils.getDaysRemainingText
import java.time.LocalDate

data class QuickTaskUiModel(
    val id: Int,
    val title: String,
    val statusText: String,
    val statusIcon: ImageVector,
    val statusColor: Color,
    val category: String,
    val priorityLabel: String,
    val priorityColor: Color,
    val isCompleted: Boolean,
    val showStrikethrough: Boolean
)

private sealed interface TaskVisualState {
    data object Completed : TaskVisualState
    data object Urgent : TaskVisualState
    data object Upcoming : TaskVisualState
}

@Composable
fun QuickTask.toUiModel(): QuickTaskUiModel {
    val theme = MaterialTheme.colorScheme
    val today = LocalDate.now()
    val taskDate = dueDate.toLocalDate()

    val visualState = when {
        isCompleted -> TaskVisualState.Completed
        taskDate <= today -> TaskVisualState.Urgent
        else -> TaskVisualState.Upcoming
    }

    val statusColor = when (visualState) {
        TaskVisualState.Completed -> theme.primary
        TaskVisualState.Urgent -> theme.error
        TaskVisualState.Upcoming -> theme.onSurfaceVariant
    }

    val statusIcon = when (visualState) {
        TaskVisualState.Completed -> Icons.Default.DoneAll
        TaskVisualState.Urgent -> Icons.Outlined.Schedule
        TaskVisualState.Upcoming -> Icons.Outlined.CalendarToday
    }

    val statusText = when (visualState) {
        TaskVisualState.Completed -> stringResource(R.string.task_done)
        else -> getDaysRemainingText(taskDate)
    }

    return QuickTaskUiModel(
        id = id,
        title = title,
        statusText = statusText,
        statusIcon = statusIcon,
        statusColor = statusColor,
        category = category,
        priorityLabel = priority.name,
        priorityColor = when (priority) {
            TaskPriority.P1 -> theme.error
            TaskPriority.P2, TaskPriority.P3 -> theme.primaryContainer
        },
        isCompleted = isCompleted,
        showStrikethrough = isCompleted
    )
}
