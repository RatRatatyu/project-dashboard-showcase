package com.example.projectdashboardshowcase.core.di

import com.example.projectdashboardshowcase.core.data.repository.FakeDashboardRepositoryImpl
import com.example.projectdashboardshowcase.core.data.repository.FakeUserRepositoryImpl
import com.example.projectdashboardshowcase.core.domain.repository.DashboardRepository
import com.example.projectdashboardshowcase.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDashboardRepository(
        fakeDashboardRepositoryImpl: FakeDashboardRepositoryImpl
    ): DashboardRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        fakeUserRepositoryImpl: FakeUserRepositoryImpl
    ): UserRepository
}