package uz.turgunboyevjurabek.sharedelementtranzitationexample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavigation(modifier: Modifier,navController: NavHostController) {
    NavHost(navController = navController , startDestination = "home" ){
        composable("animatedVisible") {
            SharedWithAnimatedVisible(navController = navController)
        }
        composable("home") {
            HomeScreen(modifier = modifier,navController = navController)
        }

    }
}