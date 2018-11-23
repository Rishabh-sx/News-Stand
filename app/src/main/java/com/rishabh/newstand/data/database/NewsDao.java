package com.rishabh.newstand.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.pojo.sources.Source;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("DELETE FROM article WHERE headlineType =:headlineType AND isSaved = 0")
    void delAllUnsavedArticlesByHeadline(String headlineType);

    @Query("SELECT COUNT(*) FROM article WHERE url = :articleUrl AND publishedAt=:publishedAt")
    int checkIfExistInSavedArticles(String articleUrl, String publishedAt);

    @Insert
    void insertArticle(Article article);

    @Query("SELECT * FROM article WHERE headlineType =:headlineType AND existInRecentList = 1")
    LiveData<List<Article>> loadArticleByHeadlineType(String headlineType);

    @Query("SELECT * FROM article WHERE isSaved = 1")
    LiveData<List<Article>> loadSavedArticleByHeadlineType();

    @Query("UPDATE article SET existInRecentList = 0 WHERE headlineType = :headlineType")
    void updateRecentArticlesByHeadlines(String headlineType);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateArticle(Article article);

    @Query("DELETE FROM sources")
    void deleteSources();

    @Insert
    void insertSources(Source source);

    @Query("SELECT * FROM sources")
    LiveData<List<Source>> loadSources();

}