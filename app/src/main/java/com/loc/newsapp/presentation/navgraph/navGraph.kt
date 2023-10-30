package com.loc.newsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.loc.newsapp.presentation.onboarding.OnBoardingViewModel
import com.loc.newsapp.presentation.onboarding.components.OnBoardingScreen

@Composable
fun NavGraph(
    startDestination : String
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){

        navigation(
            startDestination = Route.OnBoardingScreen.route,
            route = Route.AppStartNaviation.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()      //the app entry use cases
                OnBoardingScreen(
                    event = {        //basically we expose only required functions of our viewmodel to the composable rather than passing the whole view model
                        viewModel.onEvent(it)
                    }
                )
            }
        }

        navigation(
            startDestination = Route.NewsNavigatorScreen.route,
            route = Route.NewsNavigation.route
        ){
            composable(
                route = Route.NewsNavigatorScreen.route
            ){
                Text( "News nav screen")
            }
        }
    }
}