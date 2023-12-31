package com.loc.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.util.constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    private val _searchQueryState = MutableStateFlow(String())
    private val searchQueryState: StateFlow<String> get() = _searchQueryState

    fun updateSearchQuery(newQuery : String){
        _searchQueryState.value = newQuery
    }
    val news = newsUseCases.getNews(
        sources = constants.SOURCES
    ).cachedIn(viewModelScope)

    val searchedNews by lazy {
        newsUseCases.searchNews(
            sources = constants.SOURCES,
            searchQuery = searchQueryState.value
        )
    }
}