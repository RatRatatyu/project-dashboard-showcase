package com.example.projectdashboardshowcase.core.domain.usecase

import com.example.projectdashboardshowcase.core.domain.repository.DashboardRepository
import javax.inject.Inject

class TaskCheckUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
){

    operator fun invoke(taskId: Int, userId: Int){
        dashboardRepository.taskCheckDone(taskId, userId)
    }
}