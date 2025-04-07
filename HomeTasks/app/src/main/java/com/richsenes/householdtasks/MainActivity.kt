package com.richsenes.householdtasks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.richsenes.householdtasks.data.repository.FirebaseRepository
import com.richsenes.householdtasks.ui.screens.create_task.CreateTaskScreen
import com.richsenes.householdtasks.ui.screens.houses.HousesScreen
import com.richsenes.householdtasks.ui.screens.home.HomeScreen
import com.richsenes.householdtasks.ui.screens.leaderboard.LeaderboardScreen
import com.richsenes.householdtasks.ui.screens.task_list.TaskListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Household Tasks") })
        }
    ) {
        NavigationComponent(navController = navController)
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("leaderboard") { LeaderboardScreen(navController) }
        composable("create_task") { CreateTaskScreen(navController) }
        composable("task_list") { TaskListScreen(navController) }
        composable("houses") { HousesScreen(navController) }
    }
}
