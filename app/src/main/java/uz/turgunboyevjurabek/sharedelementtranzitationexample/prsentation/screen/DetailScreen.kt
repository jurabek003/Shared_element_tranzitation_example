@file:OptIn(ExperimentalSharedTransitionApi::class)

package uz.turgunboyevjurabek.sharedelementtranzitationexample.prsentation.screen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.turgunboyevjurabek.sharedelementtranzitationexample.R

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier=modifier
            .fillMaxSize()
    ) {
        with(sharedTransitionScope){
            Image(
                painter = painterResource(id = R.drawable.jam),
                contentDescription = "jam",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(200.dp)
                    .clip(CircleShape)
                    .clickable {
                        onBack()
                    }
            )

        }
    }


}