package com.loc.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.util.constants
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article : Article)

    @Query("SELECT * FROM ${constants.BM_TABLE_NAME}")
    fun getArticles() : Flow<List<Article>>

    @Query("SELECT * FROM ${constants.BM_TABLE_NAME} WHERE url =:url")
    suspend fun getArticleWithUrl(url : String) : Article?
}