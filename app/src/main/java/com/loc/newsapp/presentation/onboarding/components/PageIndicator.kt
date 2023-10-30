package com.loc.newsapp.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    modifier : Modifier = Modifier,
    pageSize : Int,
    currentPage : Int,
    currentPageColor : Color = MaterialTheme.colorScheme.primary,
    otherPageColor : Color = Color.LightGray
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        repeat(pageSize){
            Box(
                modifier = Modifier.size(10.dp).clip(CircleShape)
                    .background(color = if (it == currentPage) currentPageColor else otherPageColor)
            )
        }

    }
}