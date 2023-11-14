package com.loc.newsapp.presentation.newsDetails

sealed class DetailsEvent {
    object saveArticle : DetailsEvent()
}