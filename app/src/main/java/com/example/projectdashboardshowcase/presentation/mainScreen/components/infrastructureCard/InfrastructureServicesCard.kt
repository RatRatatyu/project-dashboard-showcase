package com.example.projectdashboardshowcase.presentation.mainScreen.components.infrastructureCard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.InfrastructureAndService
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme


@Composable
fun InfrastructureCard(
    modifier: Modifier = Modifier,
    infrastructureAndService: InfrastructureAndService,
    onShareClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        ) {

        UpperInfrastructureCard(
            imageUrl = infrastructureAndService.imageUrl,
            category = infrastructureAndService.category,
            priority = infrastructureAndService.priority,
            subTitle = infrastructureAndService.subTitle
        )

        LowerInfrastructureCard(
            name = infrastructureAndService.name,
            description = infrastructureAndService.description,
            components = infrastructureAndService.components,
            covering = infrastructureAndService.covering,
            review = infrastructureAndService.review,
            onShareClick = onShareClick
        )

    }
}


@Preview
@Composable
fun InfrastructureCardPreview() {
    val mockService = InfrastructureAndService(
        id = 10,
        imageUrl = R.drawable.service1,
        name = "Аналитический дашборд v3.2",
        description = "Унификация дата-визуализаций, многоосевых графиков и адаптивных KPI",
        priority = TaskPriority.P1,
        category = "Дизайн-система",
        subTitle = "Core UI Kit • Release candidate",
        components = 84,
        covering = 0.994f,
        review = 12
    )
    ProjectDashboardShowcaseTheme {
        InfrastructureCard(
            infrastructureAndService = mockService,
            onShareClick = {}
        )
    }
}