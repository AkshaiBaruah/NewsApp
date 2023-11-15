package com.loc.newsapp.domain.usecases.news

import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() : Flow<List<Article>>{
        return newsRepository.getArticle()
    }
}