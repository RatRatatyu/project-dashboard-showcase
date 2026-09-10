package com.example.projectdashboardshowcase.presentation.mainScreen.utils

import java.time.LocalDate
import java.time.temporal.ChronoUnit


fun getDaysRemainingText(dueDate: LocalDate): String {
    val today = LocalDate.now()
    val daysBetween = ChronoUnit.DAYS.between(today, dueDate)

    return when {
        daysBetween == 0L -> "Сегодня"
        daysBetween == 1L -> "Завтра"
        daysBetween > 1L -> "Осталось $daysBetween дн."
        else -> "Просрочено"
    }
}