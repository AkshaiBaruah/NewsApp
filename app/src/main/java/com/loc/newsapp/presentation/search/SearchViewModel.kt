package com.loc.newsapp.presentation.search

import com.loc.newsapp.domain.usecases.news.NewsUseCases
import javax.inject.Inject

class SearchViewModel@Inject constructor(
    val newsUseCases: NewsUseCases
) {
    
}