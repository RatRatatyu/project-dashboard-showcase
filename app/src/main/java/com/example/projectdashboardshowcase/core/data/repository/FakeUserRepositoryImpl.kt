package com.example.projectdashboardshowcase.core.data.repository

import com.example.projectdashboardshowcase.core.data.datasource.FakeUserDataProvider
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.core.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class FakeUserRepositoryImpl @Inject constructor(): UserRepository {

    override fun getUser(): Flow<User> =
        flow{
            delay(5.seconds)
            emit(FakeUserDataProvider.getMockUser())
        }

}