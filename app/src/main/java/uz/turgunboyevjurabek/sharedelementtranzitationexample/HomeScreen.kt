@file:OptIn(ExperimentalSharedTransitionApi::class)

package uz.turgunboyevjurabek.sharedelementtranzitationexample

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(modifier: Modifier,navController: NavHostController) {
    Column(modifier = modifier) {
        var showDetail by rememberSaveable { mutableStateOf(false) }

        SharedTransitionLayout {
            AnimatedContent(
                targetState = showDetail,
                label = "test"
            ) { targetState ->
                if (!targetState) {
                    MainScreen(
                        onShowDetails = {
                            showDetail = true
                        },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                } else {
                    DetailScreen(
                        onBack = {
                            showDetail = false
                        },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }

            }
        }
    }
}