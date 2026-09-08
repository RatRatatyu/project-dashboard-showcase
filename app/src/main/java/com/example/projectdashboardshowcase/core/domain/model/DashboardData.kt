package com.example.projectdashboardshowcase.core.domain.model

data class DashboardData(
    val userId: Int,
    val activeSprintCount: Int,
    val projectInFocus: ProjectInFocus,
    val infrastructureAndService: InfrastructureAndService,
    val quickTasks: List<QuickTask>
)