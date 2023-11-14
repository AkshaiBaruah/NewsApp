package com.loc.newsapp.presentation.newsDetails.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.newsapp.R
import com.loc.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBackClick : ()->Unit,
    onBookmarkClick: ()->Unit,
    onOpenClick:()->Unit,
    onShareClick:()->Unit,
){
    TopAppBar(
        modifier = Modifier.fillMaxWidth().shadow(elevation = 1.dp),
        title = {},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onOpenClick) {
                Icon(painter = painterResource(id = R.drawable.ic_network), contentDescription = null)
            }
            IconButton(onClick = onBookmarkClick) {
                Icon(painter = painterResource(id = R.drawable.ic_bookmark), contentDescription = null)
            }
            IconButton(onClick = onShareClick) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
fun previewDetailsTopBar(){
    NewsAppTheme(darkTheme = false) {
        DetailsTopBar(
            onBackClick = {},
            onBookmarkClick = {},
            onOpenClick = {}
        ) {

        }
    }
}