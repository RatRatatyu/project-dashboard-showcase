package com.example.projectdashboardshowcase.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.model.InfrastructureAndService
import com.example.projectdashboardshowcase.core.domain.model.ProjectInFocus
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.presentation.mainScreen.components.DashboardTopAppBar
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreenComponent(
        uiState = uiState
    )

    
}

@Composable
fun MainScreenComponent(
    modifier: Modifier = Modifier,
    uiState: MainUiState
){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { DashboardTopAppBar(userState = uiState.userState) }
    ) {innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val userState = uiState.userState) {
                is UserState.Loading -> Text("Загрузка...")
                is UserState.Success -> Text("Добрый день ${userState.user.name}")
                is UserState.Error -> Text("Ошибка")
            }
        }

    }
}




@Preview(showBackground = true)
@Composable
fun MainScreenComponentPreview() {
    val mockUser = User(100, "Анна", R.drawable.user1)
    val mockProject = ProjectInFocus(
        id = 1,
        imageUrl = R.drawable.projectinfocus1,
        dueDate = LocalDate.now(),
        categoryHub = "Архитектурный хаб",
        name = "Реконструкция павильона",
        description = "Интеграция параметрических моделей...",
        process = "В процессе",
        processDone = 0.68f,
        taskAll = 60,
        taskDone = 42,
        prMerged = 14,
        bugs = 3,
        linkedUsers = listOf(mockUser),
        useTool = listOf("React Native", "WebGL")
    )
    val mockService = InfrastructureAndService(
        id = 10,
        imageUrl = R.drawable.service1,
        name = "Аналитический дашборд",
        description = "Унификация дата-визуализаций",
        priority = TaskPriority.P1,
        category = "Дизайн-система",
        subTitle = "Core UI Kit",
        components = 84,
        covering = 0.994f,
        review = 12
    )
    val mockDashboard = DashboardData(
        userId = 100,
        activeSprintCount = 3,
        projectInFocus = mockProject,
        infrastructureAndService = mockService,
        quickTasks = emptyList()
    )

    ProjectDashboardShowcaseTheme {
        Surface {
            MainScreenComponent(
                uiState = MainUiState(
                    userState = UserState.Success(mockUser),
                    contentState = ContentState.Success(mockDashboard)
                )
            )
        }
    }
}
