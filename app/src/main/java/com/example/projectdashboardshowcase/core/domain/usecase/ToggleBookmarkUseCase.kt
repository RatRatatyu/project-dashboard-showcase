package com.example.projectdashboardshowcase.core.domain.usecase

import com.example.projectdashboardshowcase.core.domain.repository.DashboardRepository
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
){
    operator fun invoke(bookedState: Boolean, userId: Int){
        dashboardRepository.toggleProjectBookedState(bookedState, userId)
    }
}