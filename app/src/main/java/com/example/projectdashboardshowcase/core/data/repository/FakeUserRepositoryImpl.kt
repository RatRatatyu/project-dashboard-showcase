package com.example.projectdashboardshowcase.core.data.repository

import com.example.projectdashboardshowcase.core.data.datasource.FakeUserDataProvider
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.core.domain.repository.UserRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class FakeUserRepositoryImpl @Inject constructor(): UserRepository {

    override suspend fun getUser(): User {
        delay(300.milliseconds)
        return FakeUserDataProvider.getMockUser()
    }
}