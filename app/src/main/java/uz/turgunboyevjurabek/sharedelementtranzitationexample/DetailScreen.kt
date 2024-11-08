@file:OptIn(ExperimentalSharedTransitionApi::class)

package uz.turgunboyevjurabek.sharedelementtranzitationexample

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import uz.turgunboyevjurabek.sharedelementtranzitationexample.utils.SelectedItem

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val itemImage by remember { mutableStateOf(SelectedItem.selectedItemImage) }
    val item by remember { mutableStateOf(SelectedItem.selectedItem) }

    LaunchedEffect(key1 = item) {
        delay(100)  // 100 ms kechikish, bu element tayyor bo'lishini kutish uchun
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        with(sharedTransitionScope) {
            Image(
                painter = painterResource(id = R.drawable.jam),
                contentDescription = "jam",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .sharedElement(
                        rememberSharedContentState(key = itemImage),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(200.dp)
                    .clip(CircleShape)
                    .clickable { onBack() }
            )
            Text(
                text = "My friend",
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                modifier = modifier
                    .sharedElement(
                        rememberSharedContentState(key = item),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )
        }
    }
}
