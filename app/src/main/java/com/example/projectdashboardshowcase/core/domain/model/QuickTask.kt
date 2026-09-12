package com.example.projectdashboardshowcase.core.domain.model

import java.time.LocalDateTime

data class QuickTask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean,
    val priority: TaskPriority, // enum P1, P2, P3
    val dueDate: LocalDateTime,
    val category: String
)

