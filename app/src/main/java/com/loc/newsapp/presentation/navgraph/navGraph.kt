package com.loc.newsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.presentation.home.HomeScreen
import com.loc.newsapp.presentation.home.HomeViewModel
import com.loc.newsapp.presentation.onboarding.OnBoardingViewModel
import com.loc.newsapp.presentation.onboarding.components.OnBoardingScreen



@Composable
fun NavGraph(
    startDestination : String
){
    val navController = rememberNavController()
    fun navigate(route : String){
        navController.navigate(route)
    }
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
                val homeViewModel : HomeViewModel = hiltViewModel()
                val articles = homeViewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigate = {
                        navigate(it)
                    }
                )

            }
            composable(
                route = Route.SearchScreen.route + "/{searchQuery}"
            ){backStackEntry->
                val searchQuery = backStackEntry.arguments?.getString("searchQuery")?: "top-news"
                val homeViewModel : HomeViewModel = hiltViewModel()
                homeViewModel.updateSearchQuery(searchQuery)
                val articles = homeViewModel.searchedNews.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigate ={ navigate(it)} )

            }
        }
    }
}

