package com.example.authexample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authexample.screens.HomeScreen
import com.example.authexample.screens.LoginScreen
import com.example.authexample.screens.SignupScreen
import com.example.authexample.screens.TaskListScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel, taskViewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LoginScreen(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupScreen(modifier, navController, authViewModel)
        }
        composable("home") {
            HomeScreen(modifier, navController, authViewModel)
        }
        composable("taskList") {
            TaskListScreen(modifier, navController, taskViewModel)
        }
    })
}