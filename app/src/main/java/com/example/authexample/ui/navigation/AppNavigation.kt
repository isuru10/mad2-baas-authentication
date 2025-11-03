package com.example.authexample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authexample.ui.home.HomeScreen
import com.example.authexample.ui.auth.AuthViewModel
import com.example.authexample.ui.auth.LoginScreen
import com.example.authexample.ui.auth.SignupScreen
import com.example.authexample.ui.tasks.TaskListScreen
import com.example.authexample.ui.tasks.TaskViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel, taskViewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.TaskList.route,
        builder = {
            composable(AppRoutes.Login.route) {
                LoginScreen(modifier, navController, authViewModel)
            }
            composable(AppRoutes.Signup.route) {
                SignupScreen(modifier, navController, authViewModel)
            }
            composable(AppRoutes.Home.route) {
                HomeScreen(modifier, navController, authViewModel)
            }
            composable(AppRoutes.TaskList.route) {
                TaskListScreen(modifier, navController, taskViewModel)
            }
        }
    )
}