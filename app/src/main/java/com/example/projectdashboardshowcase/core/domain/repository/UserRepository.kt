package com.example.projectdashboardshowcase.core.domain.repository

import com.example.projectdashboardshowcase.core.domain.model.User

interface UserRepository {
    suspend fun getUser():User
}