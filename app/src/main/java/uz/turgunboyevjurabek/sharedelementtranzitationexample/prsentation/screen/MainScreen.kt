@file:OptIn(ExperimentalSharedTransitionApi::class)

package uz.turgunboyevjurabek.sharedelementtranzitationexample.prsentation.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.turgunboyevjurabek.sharedelementtranzitationexample.R
import uz.turgunboyevjurabek.sharedelementtranzitationexample.utils.SelectedItem

@Composable
fun MainScreen(
    onShowDetails: () -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            verticalItemSpacing = 20.dp,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(30) {it->
                with(sharedTransitionScope) {
                    ItemList(
                        modifier=modifier
                            .sharedElement(
                                rememberSharedContentState(key = "$it"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        selectedItem = it,
                        onShowDetails = onShowDetails,
                        sharedTransitionScope = sharedTransitionScope,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }

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
            onShowDetails()
            SelectedItem.selectedItem= selectedItem.toString()
        },
        shape = ShapeDefaults.Small,
        shadowElevation = 5.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "cat",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(ShapeDefaults.Small)
                .size(150.dp)

        )

    }
}
