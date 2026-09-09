package com.example.projectdashboardshowcase.core.data.repository

import com.example.projectdashboardshowcase.core.data.datasource.FakeDashboardDataProvider
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.repository.DashboardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class FakeDashboardRepositoryImpl @Inject constructor(): DashboardRepository {

    override fun getDashboardData(userId: Int): Flow<DashboardData> =
        flow{
            delay(60.milliseconds)
            emit(FakeDashboardDataProvider.getMockData(userId))
        }


    override fun updateData() {
        //TODO
    }
}