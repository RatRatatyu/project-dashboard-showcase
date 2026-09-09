package com.example.projectdashboardshowcase.core.domain.repository

import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    fun getDashboardData(userId: Int): Flow<DashboardData>
    fun updateData()

}