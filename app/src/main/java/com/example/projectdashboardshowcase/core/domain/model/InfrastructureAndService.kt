package com.example.projectdashboardshowcase.core.domain.model

data class InfrastructureAndService(
    val id: Int,
    val imageUrl: Int?,
    val name: String,
    val description: String,
    val priority: TaskPriority,
    val category: String,
    val subTitle: String,
    val components: Int,
    val covering: Float,
    val review: Int
)