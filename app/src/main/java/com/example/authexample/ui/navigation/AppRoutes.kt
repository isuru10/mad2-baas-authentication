package com.example.authexample.ui.navigation

sealed class AppRoutes(val route: String) {
    /**
     * The route for the Login screen.
     */
    object Login : AppRoutes("login")

    /**
     * The route for the Signup screen.
     */
    object Signup : AppRoutes("signup")

    /**
     * The route for the main Home screen.
     */
    object Home : AppRoutes("home")

    /**
     * The route for the Task List screen.
     */
    object TaskList : AppRoutes("taskList")

    /*
    data class TaskDetails(val taskId: Int) : AppRoutes("taskDetails/{taskId}") {
        fun createRoute(): String = "taskDetails/$taskId"
    }
    */
}