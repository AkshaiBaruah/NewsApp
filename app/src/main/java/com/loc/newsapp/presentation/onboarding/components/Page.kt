package com.loc.newsapp.presentation.onboarding.components
import androidx.annotation.DrawableRes
import com.loc.newsapp.R


data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image : Int
)

//making a list of Page
val pages = listOf<Page>(
    Page(
        title ="News across the world!" ,
        description = "MyNews Allows you to scroll through latest news around the world featuring a clean UI made with Jetpack Compose" ,
        image = R.drawable.onboarding1
    ) ,
    Page(
        title ="Search News related to anything in the world!" ,
        description = "MyNews allows you to search for news related to keywords you type" ,
        image = R.drawable.onboarding2
    ),
    Page(
        title ="Save you news!!" ,
        description = "MyNews also has the feature to save your favourite news to revisit them wheneven you want!" ,
        image = R.drawable.onboarding3
    )
)
