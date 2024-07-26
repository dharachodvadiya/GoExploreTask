package com.indie.apps.goexploretask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.indie.apps.goexploretask.ui.Destination
import com.indie.apps.goexploretask.ui.home.HomeScreen
import com.indie.apps.goexploretask.ui.second.SecondScreen
import com.indie.apps.goexploretask.ui.theme.GoExploreTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoExploreTaskTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold() { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destination.Home.route) {
                HomeScreen(onNextClick = { navController.navigate(Destination.SecondScreen.route) })
            }

            composable(Destination.SecondScreen.route) {
                SecondScreen(onNavigationUp = { navController.navigateUp() })
            }
        }

    }
}
