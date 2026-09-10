package com.example.projectdashboardshowcase.presentation.mainScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.model.InfrastructureAndService
import com.example.projectdashboardshowcase.core.domain.model.ProjectInFocus
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.presentation.mainScreen.components.ActiveUserSprints
import com.example.projectdashboardshowcase.presentation.mainScreen.components.DashboardSearchBar
import com.example.projectdashboardshowcase.presentation.mainScreen.components.DashboardTopAppBar
import com.example.projectdashboardshowcase.presentation.mainScreen.components.projectCard.ActiveProjectsCard
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    MainScreenComponent(
        uiState = uiState,
        searchQuery = searchQuery,
        onQueryChange = {string -> viewModel.onSearchQueryChanged(string)}
    )

    
}

@Composable
fun MainScreenComponent(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    searchQuery: String,
    onQueryChange: (String) -> Unit

){
    val userState = uiState.userState
    val contentState = uiState.contentState

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { DashboardTopAppBar(userState = userState) },
        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    ) {innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
                Text(
                    text = when (uiState.userState) {
                        is UserState.Loading -> stringResource(R.string.loading_profile)
                        is UserState.Success -> stringResource(
                                R.string.user_greeting,
                                userState.user.name
                            )
                        is UserState.Error -> stringResource(R.string.error_loading_profile)
                    },
                    style = MaterialTheme.typography.headlineSmall
                )

            }

            when(contentState){
                is ContentState.Loading -> {
                    items(count = 3){
                        ProjectCardSkeleton()
                    }
                }
                is ContentState.Success -> {

                    item {
                        ActiveUserSprints(activeSprintCount = contentState.content.activeSprintCount)
                    }
                    item{
                        DashboardSearchBar(
                            Modifier.padding(vertical = 10.dp),
                            query = searchQuery,
                            onQueryChange = {string -> onQueryChange(string)}
                        )
                    }
                    item{
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Stars,
                                contentDescription = stringResource(R.string.current_project_in_focus)
                            )
                            Text(
                                text = stringResource(R.string.current_project_in_focus),
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }
                    item {
                        ActiveProjectsCard(
                            projectInFocus = contentState.content.projectInFocus,
                        )
                    }


                }

                is ContentState.Error ->{}
                    //TODO
            }
        }
    }
}


@Composable
fun ProjectCardSkeleton(modifier: Modifier = Modifier){
    Card(
        modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .height(350.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
    ) {}
}


@Preview(showBackground = true)
@Composable
fun MainScreenComponentPreview() {
    val mockUsers = listOf(
        User(100, "Анна", R.drawable.user1),
        User(200, "Лайла", R.drawable.user3),
        User(400, "Никита", R.drawable.user2),
        User(300, "Петя", null),
        User(500, "Наталия", R.drawable.user3)
    )
    val mockProject = ProjectInFocus(
        id = 1,
        imageUrl = R.drawable.projectinfocus1,
        dueDate = LocalDate.of(2026, 9, 15),
        categoryHub = "Архитектурный хаб • Фронтенд & BIM",
        name = "Реконструкция павильона цифровых иноваций",
        description = "Интеграция параметрических 3D-моделей BIM в мобильный интерфейс",
        process = "В процессе",
        processDone = 0.68f,
        taskAll = 60,
        taskDone = 42,
        prMerged = 14,
        bugs = 3,
        linkedUsers = mockUsers,
        useTool = listOf("React Native", "WebGL", "Kotlin")
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
                    userState = UserState.Success(mockUsers[0]),
                    contentState = ContentState.Success(mockDashboard),
                    ),
                searchQuery = "",
                onQueryChange = {  }
            )
        }
    }
}
