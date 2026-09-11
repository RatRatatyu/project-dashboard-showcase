package com.example.projectdashboardshowcase.presentation.mainScreen.components.infrastructureCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phonelink
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority
import com.example.projectdashboardshowcase.presentation.mainScreen.utils.GetPriorityText
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme


@Composable
fun UpperInfrastructureCard(
    modifier: Modifier = Modifier,
    imageUrl: Int?,
    category: String,
    priority: TaskPriority,
    subTitle: String
) {
    val hasImage = imageUrl != null
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        if (hasImage) {
            Image(
                painter = painterResource(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                        startY = 50f
                    )
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = if (priority == TaskPriority.P1) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("!", modifier = Modifier.padding(end = 4.dp))
                    GetPriorityText(priority)
                }
            }

            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category,
                        maxLines = 2,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomStart),
        ) {
            Icon(
                imageVector = Icons.Default.Phonelink,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = subTitle,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun UpperPartPreview(){
    ProjectDashboardShowcaseTheme{
        UpperInfrastructureCard(
            imageUrl = R.drawable.service1,
            priority = TaskPriority.P1,
            category = "Дизайн-система",
            subTitle = "Core UI Kit • Release candidate",
        )
    }
}