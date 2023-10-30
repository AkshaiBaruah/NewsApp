package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow


interface LocalUserManager {         //responsible for managing if the user has entered the app(onboarding done?)
    suspend fun saveAppEntry()
    fun readAppEntry() : Flow<Boolean>
}