package com.example.projectdashboardshowcase.presentation.mainScreen.components.infrastructureCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.projectdashboardshowcase.R

@Composable
fun LowerInfrastructureCard(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    components: Int,
    covering: Float,
    review: Int,
    onShareClick: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(vertical = 5.dp)
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 500.dp)
                .padding(top = 8.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfrastructureInfoBlueprint(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Category,
                    info = components.toString(),
                    title = stringResource(R.string.component)
                )
                InfrastructureInfoBlueprint(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Verified,
                    info = "${(covering * 100).toInt()}%",
                    title = stringResource(R.string.covering)
                )
                InfrastructureInfoBlueprint(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.RateReview,
                    info = review.toString(),
                    title = stringResource(R.string.review)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(stringResource(R.string.continue_button))
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowRight,
                        contentDescription = null
                    )

                }
            }
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onShareClick,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null
                    )
                    Text(stringResource(R.string.share_info))
                }
            }
        }
    }
}


@Composable
private fun InfrastructureInfoBlueprint(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    info: String,
    title: String
) {
    Surface(
        modifier = modifier.heightIn(min = 80.dp),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = info,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}