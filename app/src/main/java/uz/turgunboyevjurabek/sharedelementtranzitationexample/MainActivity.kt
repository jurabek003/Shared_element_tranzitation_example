@file:OptIn(ExperimentalSharedTransitionApi::class)

package uz.turgunboyevjurabek.sharedelementtranzitationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.turgunboyevjurabek.sharedelementtranzitationexample.prsentation.screen.DetailScreen
import uz.turgunboyevjurabek.sharedelementtranzitationexample.prsentation.screen.MainScreen
import uz.turgunboyevjurabek.sharedelementtranzitationexample.ui.theme.SharedElementTranzitationExampleTheme
import uz.turgunboyevjurabek.sharedelementtranzitationexample.utils.SelectedItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedElementTranzitationExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        var showDetail by rememberSaveable { mutableStateOf(false) }

                        SharedTransitionLayout {
                            AnimatedContent(
                                targetState = showDetail,
                                label = "test"
                            ) { targetState ->
                                if (!targetState){
                                    MainScreen(
                                        onShowDetails = {
                                            showDetail=true
                                        },
                                        animatedVisibilityScope = this@AnimatedContent,
                                        sharedTransitionScope = this@SharedTransitionLayout
                                    )
                                }else{
                                    DetailScreen(
                                        onBack = {
                                            showDetail=false
                                        },
                                        animatedVisibilityScope = this@AnimatedContent,
                                        sharedTransitionScope = this@SharedTransitionLayout
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
