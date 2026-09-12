package com.example.projectdashboardshowcase.core.data.datasource

import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.DashboardData
import com.example.projectdashboardshowcase.core.domain.model.InfrastructureAndService
import com.example.projectdashboardshowcase.core.domain.model.ProjectInFocus
import com.example.projectdashboardshowcase.core.domain.model.QuickTask
import com.example.projectdashboardshowcase.core.domain.model.TaskPriority
import com.example.projectdashboardshowcase.core.domain.model.User
import java.time.LocalDate
import java.time.LocalDateTime

// Here we fake server feed
private val userAnna = User(100, "Анна", R.drawable.user1)
private val userAlex = User(120, "Алексей", null)
private val userMaria = User(138, "Мария", null)
private val userIvan = User(101, "Иван", R.drawable.user3)
private val userElena = User(102, "Елена", R.drawable.user2)

private val sharedDesignSystem = InfrastructureAndService(
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

val data: List<DashboardData> = listOf(
    DashboardData(
        userId = 100,
        activeSprintCount = 3,
        projectInFocus = ProjectInFocus(
            id = 1,
            imageUrl = R.drawable.projectinfocus1,
            dueDate = LocalDate.now().plusDays(4),
            categoryHub = "Архитектурный хаб • Фронтенд & BIM",
            name = "Реконструкция павильона цифровых инноваций",
            description = "Интеграция параметрических 3D-моделей BIM в мобильный интерфейс...",
            process = "В процессе",
            processDone = 0.68f,
            taskAll = 60,
            taskDone = 42,
            prMerged = 14,
            bugs = 3,
            linkedUsers = listOf(userIvan, userAlex, userMaria, userElena),
            useTool = listOf("React Native", "WebGL", "BIM v2.4")
        ),
        infrastructureAndService = sharedDesignSystem,
        quickTasks = listOf(
            QuickTask(
                id = 1,
                title = "Оптимизировать шейдеры освещения",
                isCompleted = false,
                priority = TaskPriority.P1,
                dueDate = LocalDateTime.of(2026,9,15,15,0),
                category = "Павильон инноваций"
            ),
            QuickTask(
                id = 2,
                title = "Синхронизировать токены Tailwind",
                isCompleted = true,
                priority = TaskPriority.P2,
                dueDate = LocalDateTime.of(2026,9,10,10,0),
                category = "Дизайн-система"
            ),
            QuickTask(
                id = 3,
                title = "Ревью PR #182: Проверка откликов",
                isCompleted = false,
                priority = TaskPriority.P3,
                dueDate = LocalDateTime.of(2026,9,12,9,0),
                category = "Телеметрия BIM"
            )
        )
    ),

    DashboardData(
        userId = 120,
        activeSprintCount = 5,
        projectInFocus = ProjectInFocus(
            id = 1,
            imageUrl = null,
            dueDate = LocalDate.now().plusDays(4),
            categoryHub = "Архитектурный хаб • Фронтенд & BIM",
            name = "Реконструкция павильона цифровых инноваций",
            description = "Оптимизация рендеринга 3D-сцен и подготовка релизной сборки.",
            process = "В процессе",
            processDone = 0.68f,
            taskAll = 60,
            taskDone = 42,
            prMerged = 14,
            bugs = 3,
            linkedUsers = listOf(userAnna, userAlex, userIvan),
            useTool = listOf("React Native", "WebGL", "BIM v2.4")
        ),
        infrastructureAndService = sharedDesignSystem,
        quickTasks = listOf(
            QuickTask(
                id = 1,
                title = "Настроить CI/CD пайплайн для WebGL",
                isCompleted = false,
                priority = TaskPriority.P1,
                dueDate = LocalDateTime.of(2026,10,15,15,0),
                category = "Инфраструктура"
            ),
            QuickTask(
                id = 2,
                title = "Провести профилирование памяти",
                isCompleted = true,
                priority = TaskPriority.P2,
                dueDate = LocalDateTime.of(2026,9,15,15,0),
                category = "Павильон инноваций"
            )
        )
    ),


    DashboardData(
        userId = 138,
        activeSprintCount = 2,
        projectInFocus = ProjectInFocus(
            id = 2,
            imageUrl = null,
            dueDate = LocalDate.now().plusDays(12),
            categoryHub = "Аналитика & Телеметрия",
            name = "Платформа сбора BIM-метрики",
            description = "Разработка модуля обработки данных датчиков в реальном времени.",
            process = "Планирование",
            processDone = 0.25f,
            taskAll = 40,
            taskDone = 10,
            prMerged = 5,
            bugs = 1,
            linkedUsers = listOf(userMaria, userElena),
            useTool = listOf("Kotlin", "Ktor", "ClickHouse")
        ),
        infrastructureAndService = InfrastructureAndService(
            id = 11,
            imageUrl = null,
            name = "Сервис логирования и мониторинга",
            description = "Централизованный сбор логов и алертинг по инцидентам",
            priority = TaskPriority.P2,
            category = "Телеметрия BIM",
            subTitle = "DevOps Tooling • Active",
            components = 18,
            covering = 0.88f,
            review = 4
        ),
        quickTasks = listOf(
            QuickTask(
                id = 1,
                title = "Обновить API эндпоинты для телеметрии",
                isCompleted = false,
                priority = TaskPriority.P1,
                dueDate = LocalDateTime.of(2026,8,15,15,0),
                category = "Телеметрия BIM"
            )
        )
    )
)