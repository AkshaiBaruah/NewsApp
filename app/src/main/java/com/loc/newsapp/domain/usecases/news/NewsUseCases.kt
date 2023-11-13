package com.loc.newsapp.domain.usecases.news

import javax.inject.Inject

data class NewsUseCases @Inject constructor(
    val getNews: GetNews,
    val searchNews: SearchNews
)
