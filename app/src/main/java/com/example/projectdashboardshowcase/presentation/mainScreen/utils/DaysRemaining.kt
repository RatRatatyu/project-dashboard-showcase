package com.example.projectdashboardshowcase.presentation.mainScreen.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import com.example.projectdashboardshowcase.R


@Composable
fun getDaysRemainingText(dueDate: LocalDate): String {
    val today = LocalDate.now()

    val daysBetween = ChronoUnit.DAYS.between(today, dueDate)
    val monthsBetween = ChronoUnit.MONTHS.between(today, dueDate)

    return when {
        daysBetween < 0L -> stringResource(R.string.overdue)
        daysBetween == 0L -> stringResource(R.string.today)
        daysBetween == 1L -> stringResource(R.string.tomorrow)


        monthsBetween >= 1L -> {
            val months = monthsBetween.toInt()
            pluralStringResource(
                id = R.plurals.months_remaining,
                count = months,
                months
            )
        }

        else -> {
            val days = daysBetween.toInt()
            pluralStringResource(
                id = R.plurals.days_remaining,
                count = days,
                days
            )
        }
    }
}