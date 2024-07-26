package com.indie.apps.goexploretask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                HomeScreen(onNextClick = { sound, place ->
                    navController.navigate(
                        Destination.SecondScreen.route
                            .replace("{sound}", sound)
                            .replace("{place}", place)
                    )
                })
            }

            composable(
                Destination.SecondScreen.route,
                arguments = listOf(
                    navArgument("sound") {
                        type = NavType.StringType
                        nullable = true
                    },
                    navArgument("place") {
                        type = NavType.StringType
                        nullable = true
                    }
                )
            ) { backStackEntry ->
                val sound = backStackEntry.arguments?.getString("sound")
                val place = backStackEntry.arguments?.getString("place")
                SecondScreen(
                    sound = sound,
                    place = place
                )
            }
        }

    }
}

@SuppressLint("RestrictedApi")
fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}
