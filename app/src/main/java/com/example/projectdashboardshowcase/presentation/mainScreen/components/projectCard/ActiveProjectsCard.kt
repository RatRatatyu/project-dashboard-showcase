package com.example.projectdashboardshowcase.presentation.mainScreen.components.projectCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.ProjectInFocus
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme
import java.time.LocalDate


@Composable
fun ActiveProjectsCard(
    modifier: Modifier = Modifier,
    projectInFocus: ProjectInFocus,
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {

        UpperCardPart(
            imageUrl = projectInFocus.imageUrl,
            process = projectInFocus.process,
            categoryHub = projectInFocus.categoryHub,
            dueDate = projectInFocus.dueDate,
            onEditClick = { showDialog = true }
        )

        LowerCardPart(
            name = projectInFocus.name,
            description = projectInFocus.description,
            processDone = projectInFocus.processDone,
            taskDone = projectInFocus.taskDone,
            taskAll = projectInFocus.taskAll,
            prMerged = projectInFocus.prMerged,
            bugs = projectInFocus.bugs,
            linkedUsers = projectInFocus.linkedUsers,
            useTool = projectInFocus.useTool
        )

        if (showDialog) {
            EditProjectDialog(
                userCount = projectInFocus.linkedUsers.size,
                onDismiss = { showDialog = false }
            )
        }


    }
}

@Preview
@Composable
fun ProjectCardPreview(){
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
    ProjectDashboardShowcaseTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            ActiveProjectsCard(
                projectInFocus = mockProject,
            )
        }
    }

}


