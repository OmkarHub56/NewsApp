package com.nwsapp.nynewsappjava;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;

@Entity(tableName = "MyNewsTable")
public class OneBookMarkedNewsItem {
    @ColumnInfo(name = "title")
    @NonNull
    @PrimaryKey String title;
    @ColumnInfo(name = "author")String author;
    @ColumnInfo(name = "content")String content;
    @ColumnInfo(name = "description")String description;
    @ColumnInfo(name = "publishedat")String publishedAt;
    @ColumnInfo(name = "url")String url;
    @ColumnInfo(name = "urltoimage")String urlToImage;

    public OneBookMarkedNewsItem(String title, String author, String content, String description, String publishedAt, String url, String urlToImage) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.description = description;
        this.publishedAt = publishedAt;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
