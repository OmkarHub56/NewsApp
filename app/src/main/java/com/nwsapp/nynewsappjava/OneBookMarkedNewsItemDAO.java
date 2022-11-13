package com.nwsapp.nynewsappjava;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OneBookMarkedNewsItemDAO {
    @Query("SELECT * from mynewstable where title=:title")
    List<OneBookMarkedNewsItem> getOneBookmarkedNews(String title);

    @Query("SELECT * from mynewstable")
    List<OneBookMarkedNewsItem> getAllBookmarkedNews();

    @Insert
    void addNewBookMarkedNews(OneBookMarkedNewsItem news);

    @Delete
    void deleteBookMarkedNews(OneBookMarkedNewsItem news);


}
