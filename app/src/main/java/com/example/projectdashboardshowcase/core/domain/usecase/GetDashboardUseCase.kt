package com.example.projectdashboardshowcase.core.domain.usecase

import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.repository.DashboardRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetDashboardUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(userId: Int): Flow<DashboardData> =
        dashboardRepository.getDashboardData(userId)

}