package com.example.projectdashboardshowcase.presentation.mainScreen.components.quickTaskCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectdashboardshowcase.core.domain.model.QuickTask
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme
import java.time.LocalDateTime


@Composable
fun QuickTaskCard(
    modifier: Modifier = Modifier,
    uiModel: QuickTaskUiModel,
    onTaskDone: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (uiModel.isCompleted) 0.6f else 1f)
            .clickable(onClick = { onTaskDone(uiModel.id) }),
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Checkbox
            Checkbox(
                checked = uiModel.isCompleted,
                onCheckedChange = { onTaskDone(uiModel.id) },
                modifier = Modifier.size(24.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Text Content
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = uiModel.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        textDecoration = if (uiModel.showStrikethrough) TextDecoration.LineThrough else null,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Surface(
                        modifier = Modifier.padding(start = 8.dp),
                        color = uiModel.priorityColor,
                        shape = CircleShape,
                    ) {
                        Text(
                            text = uiModel.priorityLabel,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = uiModel.statusIcon,
                        contentDescription = null,
                        tint = uiModel.statusColor,
                        modifier = Modifier.size(14.dp)
                    )
                    
                    Text(
                        text = uiModel.statusText,
                        style = MaterialTheme.typography.labelSmall,
                        color = uiModel.statusColor
                    )

                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )

                    Text(
                        text = uiModel.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun QuickTaskPreview() {
    ProjectDashboardShowcaseTheme {
        val mockTask = QuickTask(
            id = 1,
            title = "Оптимизировать шейдеры освещения",
            isCompleted = false,
            priority = TaskPriority.P1,
            dueDate = LocalDateTime.now(),
            category = "Павильон инноваций"
        )
        
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Active (Today)", style = MaterialTheme.typography.labelSmall)
            QuickTaskCard(uiModel = mockTask.toUiModel(), onTaskDone = {})
            
            Text("Completed", style = MaterialTheme.typography.labelSmall)
            QuickTaskCard(uiModel = mockTask.copy(isCompleted = true).toUiModel(), onTaskDone = {})

            Text("Upcoming", style = MaterialTheme.typography.labelSmall)
            QuickTaskCard(uiModel = mockTask.copy(dueDate = LocalDateTime.now().plusDays(5)).toUiModel(), onTaskDone = {})
        }
    }
}
