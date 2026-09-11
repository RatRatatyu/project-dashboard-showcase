package com.example.projectdashboardshowcase.presentation.mainScreen.components.projectCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DonutLarge
import androidx.compose.material.icons.filled.InsertChartOutlined
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.presentation.mainScreen.utils.DefaultUserAvatar
import kotlin.math.roundToInt

@Composable
fun LowerCardPart(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    processDone: Float,
    taskDone: Int,
    taskAll: Int,
    prMerged: Int,
    bugs: Int,
    linkedUsers: List<User>,
    useTool: List<String>

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

        // sprint info
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ){
                    Icon(
                        imageVector = Icons.Default.DonutLarge,
                        contentDescription = stringResource(R.string.sprint_execution),
                        modifier = Modifier
                            .padding(horizontal = 5.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(stringResource(R.string.sprint_execution))

                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${(processDone * 100).roundToInt()}%",
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                LinearProgressIndicator(
                    progress = {processDone},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(8.dp)

                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SprintInformationBlueprint(
                        title = stringResource(R.string.tasks),
                        info = "${taskDone}/${taskAll}%"
                    )
                    SprintInformationBlueprint(
                        title = stringResource(R.string.rp_merged),
                        info = "$prMerged req",
                        infoColor = MaterialTheme.colorScheme.primary
                    )
                    SprintInformationBlueprint(
                        title = stringResource(R.string.blokers),
                        info = "$bugs бага",
                        titleColor = MaterialTheme.colorScheme.error,
                        infoColor = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        // users and tools
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy((-12).dp)
                ) {
                    val maxAvatars = 3
                    val displayUsers = linkedUsers.take(maxAvatars)

                    displayUsers.forEachIndexed { index, user ->
                        UserAvatarItem(
                            user = user,
                            modifier = Modifier
                                .zIndex(index.toFloat())
                        )
                    }

                    if (linkedUsers.size > maxAvatars) {
                        val remainingCount = linkedUsers.size - maxAvatars
                        NextCounterItem(
                            count = remainingCount,
                            modifier = Modifier
                                .zIndex(displayUsers.size.toFloat())

                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 15.dp)
            ) {
                useTool.chunked(2).forEach { rowTools ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        rowTools.forEach { tool ->
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 8.dp)
                            ) {
                                Text(
                                    text = tool,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

            }
        }
        //action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text =stringResource(R.string.go_to_sprint),
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = stringResource(R.string.go_to_sprint)
                    )
                }
            }
            FilledIconButton(
                onClick = {},
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.InsertChartOutlined,
                    contentDescription = "Статистика"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            FilledIconButton(
                onClick = {},
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Tune ,
                    contentDescription = stringResource(R.string.search_by_filters)
                )
            }
        }

    }
}


@Composable
private fun NextCounterItem(
    modifier: Modifier = Modifier,
    count: Int,
){
    Box(
        modifier = modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "+$count",
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }

}
@Composable
private fun UserAvatarItem(
    modifier: Modifier = Modifier,
    user: User
){
    Box(
        modifier = modifier
            .size(30.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ){
        if (user.avatarUrl != null){
            Image(
                painter = painterResource(user.avatarUrl),
                contentDescription = stringResource(R.string.user_avatar),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)

            )
        } else{
            DefaultUserAvatar(
                userName = user.name
            )
        }
    }

}
@Composable
private fun SprintInformationBlueprint(
    modifier: Modifier = Modifier,
    title: String,
    info: String,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    infoColor: Color = MaterialTheme.colorScheme.onSurface
){
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = titleColor
            )
            Text(
                text = info,
                color = infoColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}