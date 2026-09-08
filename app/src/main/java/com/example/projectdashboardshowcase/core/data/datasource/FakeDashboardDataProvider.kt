package com.example.projectdashboardshowcase.core.data.datasource

import com.example.projectdashboardshowcase.core.domain.model.*


// We use this object to fake dashboard data from server
object FakeDashboardDataProvider {
    fun getMockData(userId: Int): DashboardData {
        return data.firstOrNull { it.userId == userId } ?: data.first()
    }
}