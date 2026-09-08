package com.example.projectdashboardshowcase.core.data.repository

import com.example.projectdashboardshowcase.core.data.datasource.FakeDashboardDataProvider
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.repository.DashboardRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class FakeDashboardRepositoryImpl @Inject constructor(): DashboardRepository {

    override suspend fun getDashboardData(userId: Int): DashboardData {
        delay(60.milliseconds)
        return FakeDashboardDataProvider.getMockData(userId)
    }

    override fun updateData() {
        //TODO
    }
}