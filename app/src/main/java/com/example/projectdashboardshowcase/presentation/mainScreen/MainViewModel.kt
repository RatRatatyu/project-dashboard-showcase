package com.example.projectdashboardshowcase.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.core.domain.usecase.GetDashboardUseCase
import com.example.projectdashboardshowcase.core.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class MainUiState(
    val userState: UserState = UserState.Loading,
    val contentState: ContentState = ContentState.Loading,

)

sealed interface UserState {
    data object Loading : UserState
    data class Success(val user: User) : UserState
    data class Error(val message: String) : UserState
}

sealed interface ContentState {
    data object Loading : ContentState
    data class Success(val content: DashboardData) : ContentState
    data class Error(val message: String) : ContentState
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDashboardUseCase: GetDashboardUseCase,
    getUserUseCase: GetUserUseCase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<MainUiState> = getUserUseCase()
        .flatMapLatest { user ->
            getDashboardUseCase(user.id)
                .map { content ->
                    MainUiState(
                        userState = UserState.Success(user),
                        contentState = ContentState.Success(content)
                    )
                }
                .onStart {
                    emit(
                        MainUiState(
                            userState = UserState.Success(user),
                            contentState = ContentState.Loading
                        )
                    )
                }
                .catch { e ->
                    emit(
                        MainUiState(
                            userState = UserState.Success(user),
                            contentState = ContentState.Error(e.message ?: "Content Error")
                        )
                    )
                }
        }
        .onStart {
            emit(MainUiState(userState = UserState.Loading, contentState = ContentState.Loading))
        }
        .catch { e ->
            emit(
                MainUiState(
                    userState = UserState.Error(e.message ?: "User Error"),
                    contentState = ContentState.Error("Dependency failed")
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MainUiState()
        )


    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

}