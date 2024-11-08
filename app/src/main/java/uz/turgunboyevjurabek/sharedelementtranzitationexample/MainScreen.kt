@file:OptIn(ExperimentalSharedTransitionApi::class)

package uz.turgunboyevjurabek.sharedelementtranzitationexample

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.sharedelementtranzitationexample.utils.SelectedItem

@Composable
fun MainScreen(
    onShowDetails: () -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(80.dp),
            contentPadding = PaddingValues(20.dp),
            verticalItemSpacing = 20.dp,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(30) { it ->
                    ItemList(
                        modifier = modifier,
                        selectedItem = it,
                        onShowDetails = onShowDetails,
                        sharedTransitionScope = sharedTransitionScope,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }
        }
    }


@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    onShowDetails: () -> Unit,
    selectedItem: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Surface(
        onClick = {
            SelectedItem.selectedItemImage = selectedItem.toString()
            SelectedItem.selectedItem = "Item - $selectedItem"
            onShowDetails()
        },
        shape = ShapeDefaults.Small,
        shadowElevation = 3.dp,
        modifier = modifier
            .size(150.dp)
    ) {
        /**
         * sharedBounds -> Row ,Column ... lar uchun ishlaydi.
         * sharedElement -> Elementlar uchun ishlaydi.
         */
            with(sharedTransitionScope){
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier=modifier
                        .sharedBounds(
                            rememberSharedContentState(key = "$selectedItem"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds(),
                        )
                ) {
                Image(
                    painter = painterResource(id = R.drawable.cat),
                    contentDescription = "cat",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
//                        .sharedElement(
//                            rememberSharedContentState(key = "$selectedItem"),
//                            animatedVisibilityScope = animatedVisibilityScope
//                        )
                        .fillMaxWidth()
                        .height(90.dp)
                        .clip(ShapeDefaults.Small)

                )
                Text(
                    text = "Item - $selectedItem",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    modifier = modifier.fillMaxWidth()
//                        .sharedElement(
//                            rememberSharedContentState(key = "Item - $selectedItem"),
//                            animatedVisibilityScope = animatedVisibilityScope
//                        )
                )
            }
        }
    }
}

