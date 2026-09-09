package com.example.projectdashboardshowcase.core.domain.repository

import com.example.projectdashboardshowcase.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User>
}