package com.example.projectdashboardshowcase.core.domain.usecase

import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<User> =
        userRepository.getUser()
}