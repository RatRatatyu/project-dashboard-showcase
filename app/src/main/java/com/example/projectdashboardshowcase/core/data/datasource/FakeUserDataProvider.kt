package com.example.projectdashboardshowcase.core.data.datasource

import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.R

// We use this object to fake user's data from DataStore
object FakeUserDataProvider {
    fun getMockUser(): User = User(
            id = 100,
            name = "Анна",
            avatarUrl = R.drawable.user1
        )
}