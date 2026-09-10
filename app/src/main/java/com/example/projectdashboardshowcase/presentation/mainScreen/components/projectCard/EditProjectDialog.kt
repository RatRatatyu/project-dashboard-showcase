package com.example.projectdashboardshowcase.presentation.mainScreen.components.projectCard

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme


@Composable
fun EditProjectDialog(
    userCount: Int,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismiss) {
        EditProjectDialogContent(userCount = userCount, modifier = modifier)
    }
}

@Composable
fun EditProjectDialogContent(
    userCount: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(0.9f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "ДЕЙСТВИЕ С ПРОЕКТОМ",
                style = MaterialTheme.typography.labelSmall
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            projectMenuItems.forEach { item ->
                if (item.hasDivider) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
                
                val badgeText = if (item.titleRes == R.string.menu_members) {
                    stringResource(R.string.menu_members_badge_dynamic, userCount)
                } else if (item.badgeRes != null) {
                    stringResource(item.badgeRes)
                } else {
                    null
                }

                ProjectMenuRow(item = item, badgeText = badgeText)
            }
        }
    }
}

@Composable
fun ProjectMenuRow(
    item: ProjectMenuItem,
    badgeText: String? = null
) {
    val contentColor = if (item.isCritical) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
    val iconBgColor = if (item.isCritical) MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{ }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // icons
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(8.dp),
            color = iconBgColor
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = contentColor
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // title / subtitle
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(item.titleRes),
                color = contentColor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            if (item.subtitleRes != null) {
                Text(
                    text = stringResource(item.subtitleRes),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        // badge
        if (badgeText != null) {
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                shape = CircleShape
            ) {
                Text(
                    text = badgeText,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    ProjectDashboardShowcaseTheme {
        EditProjectDialogContent(userCount = 10)
    }
}