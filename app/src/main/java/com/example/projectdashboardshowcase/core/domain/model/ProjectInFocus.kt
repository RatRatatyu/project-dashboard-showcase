package com.example.projectdashboardshowcase.core.domain.model

import java.time.LocalDate

data class ProjectInFocus (
    val id: Int,
    val imageUrl: String?,
    val dueDate: LocalDate,
    val categoryHub: String,
    val name: String,
    val description: String,
    val process: String,
    val processDone: Float,
    val taskAll: Int,
    val taskDone: Int,
    val prMerged: Int,
    val bugs: Int,
    val linkedUsers: List<User>,
    val useTool: List<String>
)