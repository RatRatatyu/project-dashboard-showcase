package com.example.projectdashboardshowcase.presentation.mainScreen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Layers
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.model.InfrastructureAndService
import com.example.projectdashboardshowcase.core.domain.model.ProjectInFocus
import com.example.projectdashboardshowcase.core.domain.model.QuickTask
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.presentation.mainScreen.components.ActiveUserSprints
import com.example.projectdashboardshowcase.presentation.mainScreen.components.DashboardSearchBar
import com.example.projectdashboardshowcase.presentation.mainScreen.components.DashboardTopAppBar
import com.example.projectdashboardshowcase.presentation.mainScreen.components.infrastructureCard.InfrastructureCard
import com.example.projectdashboardshowcase.presentation.mainScreen.components.projectCard.ActiveProjectsCard
import com.example.projectdashboardshowcase.presentation.mainScreen.components.quickTaskCard.QuickTaskCard
import com.example.projectdashboardshowcase.presentation.mainScreen.components.quickTaskCard.toUiModel
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme
import java.time.LocalDate
import java.time.LocalDateTime

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
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { DashboardTopAppBar(userState = userState) },
    ) {innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
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
                            query = searchQuery,
                            onQueryChange = {string -> onQueryChange(string)}
                        )
                    }
                    item{
                        TitleForContentCards(
                            icon = Icons.Default.Stars,
                            title = stringResource(R.string.current_project_in_focus)
                        )
                    }
                    item {
                        ActiveProjectsCard(
                            projectInFocus = contentState.content.projectInFocus,
                        )
                    }
                    item{
                        TitleForContentCards(
                            icon = Icons.Default.Layers,
                            title = stringResource(R.string.infrastructure_and_services)
                        )
                    }
                    item {
                        InfrastructureCard(
                            infrastructureAndService = contentState.content.infrastructureAndService,
                            onShareClick = {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "https://google.com")
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                runCatching {
                                    context.startActivity(shareIntent)
                                }
                            }
                        )
                    }
                    item {
                        TitleForContentCards(
                            icon = Icons.Default.Checklist,
                            title = stringResource(R.string.quick_tasks),
                            subTitle = stringResource(
                                R.string.appointed_tasks,
                                contentState.content.quickTasks.size
                            )
                        )
                    }
                    items(
                        items = contentState.content.quickTasks,
                        key = { it.id }
                    ) { task ->
                        QuickTaskCard(uiModel = task.toUiModel())
                    }
                }
                is ContentState.Error ->{}
                    //TODO
            }
        }
    }
}

@Composable
fun TitleForContentCards(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    subTitle: String? = null
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(
            imageVector = icon,
            contentDescription = title
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 5.dp)
        )
        Spacer(Modifier.weight(1f))

        subTitle?.let {
            Text(
                text = subTitle,
                modifier = Modifier.padding(end = 5.dp)
            )
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


@Preview(
    showBackground = true,
    heightDp = 2000,
    widthDp = 400
)
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
    val mockQuickTask = listOf(
    QuickTask(
        id = 1,
        title = "Оптимизировать шейдеры освещения",
        isCompleted = false,
        priority = TaskPriority.P1,
        dueDate = LocalDateTime.of(2026,9,15,15,0),
        category = "Павильон инноваций"
    ),
    QuickTask(
        id = 2,
        title = "Синхронизировать токены Tailwind",
        isCompleted = true,
        priority = TaskPriority.P2,
        dueDate = LocalDateTime.of(2026,9,10,10,0),
        category = "Дизайн-система"
    ),
    QuickTask(
        id = 3,
        title = "Ревью PR #182: Проверка откликов",
        isCompleted = false,
        priority = TaskPriority.P3,
        dueDate = LocalDateTime.of(2026,9,12,9,0),
        category = "Телеметрия BIM"
    )
    )
    val mockDashboard = DashboardData(
        userId = 100,
        activeSprintCount = 3,
        projectInFocus = mockProject,
        infrastructureAndService = mockService,
        quickTasks = mockQuickTask
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
