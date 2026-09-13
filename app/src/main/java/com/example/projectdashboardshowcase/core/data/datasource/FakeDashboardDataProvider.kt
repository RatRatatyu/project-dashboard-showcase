package com.example.projectdashboardshowcase.core.data.datasource

import android.util.Log
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map


// We use this object to fake dashboard data from server
object FakeDashboardDataProvider {

    private val _localData = MutableStateFlow(data)

    fun observeMockData(userId: Int): Flow<DashboardData> {
        return _localData.map { list ->
            list.firstOrNull { it.userId == userId } ?: list.first()
        }
    }

    fun toggleState(state: Boolean, userId: Int){
        val currentList = _localData.value.toMutableList()

        val index = currentList.indexOfFirst { it.userId == userId }

        if (index != -1) {
            currentList[index] = currentList[index].copy(
                projectInFocus = currentList[index].projectInFocus.copy(isBookmarked = state)
            )

            _localData.value = currentList
        }
        Log.i("UPDATE_CHECK", "isBooked update to $state")
    }
}