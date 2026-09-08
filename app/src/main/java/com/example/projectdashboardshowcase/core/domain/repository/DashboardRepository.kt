package com.example.projectdashboardshowcase.core.domain.repository

import com.example.projectdashboardshowcase.core.domain.model.DashboardData

interface DashboardRepository {

    suspend fun getDashboardData(userId: Int): DashboardData
    fun updateData()

}