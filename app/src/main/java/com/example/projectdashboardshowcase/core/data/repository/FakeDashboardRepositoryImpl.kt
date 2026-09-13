package com.example.projectdashboardshowcase.core.data.repository

import com.example.projectdashboardshowcase.core.data.datasource.FakeDashboardDataProvider
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.repository.DashboardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class FakeDashboardRepositoryImpl @Inject constructor(): DashboardRepository {

    override fun getDashboardData(userId: Int): Flow<DashboardData> =
        FakeDashboardDataProvider.observeMockData(userId)
            .onStart {
                delay(1.seconds)
            }


    override fun toggleProjectBookedState(bookedState: Boolean, userId: Int){
        FakeDashboardDataProvider.toggleState(bookedState, userId)
    }

    override fun taskCheckDone(taskId: Int, userId: Int) {
        FakeDashboardDataProvider.taskCheckDone(taskId, userId)
    }
}