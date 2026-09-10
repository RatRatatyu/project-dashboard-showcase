package com.example.projectdashboardshowcase.presentation.mainScreen.components.projectCard

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Tune
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projectdashboardshowcase.R

data class ProjectMenuItem(
    val icon: ImageVector,
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int? = null,
    @StringRes val badgeRes: Int? = null,
    val isCritical: Boolean = false,
    val hasDivider: Boolean = false
)

val projectMenuItems = listOf(
    ProjectMenuItem(
        icon = Icons.Default.Edit,
        titleRes = R.string.menu_edit,
        subtitleRes = R.string.menu_edit_sub
    ),
    ProjectMenuItem(
        icon = Icons.Default.Link,
        titleRes = R.string.menu_share,
        badgeRes = R.string.menu_share_badge
    ),
    ProjectMenuItem(
        icon = Icons.Default.Groups,
        titleRes = R.string.menu_members,
        badgeRes = R.string.menu_members_badge
    ),
    ProjectMenuItem(
        icon = Icons.Default.ContentCopy,
        titleRes = R.string.menu_duplicate
    ),
    ProjectMenuItem(
        icon = Icons.Default.Tune,
        titleRes = R.string.menu_sprint_settings,
        hasDivider = true
    ),
    ProjectMenuItem(
        icon = Icons.Default.Archive,
        titleRes = R.string.menu_archive
    ),
    ProjectMenuItem(
        icon = Icons.Default.Delete,
        titleRes = R.string.menu_delete,
        isCritical = true
    )
)