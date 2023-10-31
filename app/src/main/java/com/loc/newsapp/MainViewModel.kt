package com.loc.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.appEntry.AppEntryUseCases
import com.loc.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var isSplash by mutableStateOf(true)
        private set       //public getter , private setter

    var startDestination by mutableStateOf(Route.AppStartNaviation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach {appEntry ->
            if(appEntry){
                startDestination = Route.NewsNavigation.route
            }
            else{
                startDestination = Route.AppStartNaviation.route
            }
            delay(300)
            isSplash = false
        }.launchIn(viewModelScope)
    }
}