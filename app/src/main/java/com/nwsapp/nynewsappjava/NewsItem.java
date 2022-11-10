package com.nwsapp.nynewsappjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NewsItem {
    private String author,title,url,urlToImage,publishedAt,content,description;

    public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt,String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content=content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class FullNews{
    private String status;
    private String totRes;
    private List<NewsItem> articles;

    public FullNews(String status, String totRes, List<NewsItem> articles) {
        this.status = status;
        this.totRes = totRes;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotRes() {
        return totRes;
    }

    public void setTotRes(String totRes) {
        this.totRes = totRes;
    }

    public List<NewsItem> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsItem> articles) {
        this.articles = articles;
    }
}

class ApiUtilities{
    private static Retrofit retrofit=null;

    public static ApiInterface getApiInterface(){
        if(retrofit==null){
            System.out.println("lolkn");
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}

interface ApiInterface{
    String BASE_URL="https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<FullNews> getNews(
            @Query("pageSize") int pageSize,
            @Query("category") String category,
            @Query("apiKey") String apikey,
            @Query("country") String country
    );

    @GET("top-headlines")
    Call<FullNews> getKeywordNews(
        @Query("apiKey") String apikey,
        @Query("pageSize") int pageSize,
        @Query("q") String q,
        @Query("country") String country
    );
}



class GFullNews{
    int totalArticles;
    List<OneArticle> articles;

    public GFullNews(int totalArticles, List<OneArticle> articles) {
        this.totalArticles = totalArticles;
        this.articles = articles;
    }

    public int getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(int totalArticles) {
        this.totalArticles = totalArticles;
    }

    public List<OneArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<OneArticle> articles) {
        this.articles = articles;
    }
}

class OneArticle{
    String title,description,content,url,image,publishedAt;
    Source source;

    public OneArticle(String title, String description, String content, String url, String image, String publishedAt, Source source) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}

class Source{
    String name,url;

    public Source(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
class ApiUtilities2{
    private static Retrofit retrofit=null;

    public static ApiInterface2 getApiInterface(){
        if(retrofit==null){
            System.out.println("lolkn");
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface2.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface2.class);
    }
}

interface ApiInterface2{
    String BASE_URL="https://gnews.io/api/v4/";

    @GET("top-headlines")
    Call<GFullNews> getNews(
            @Query("max") int max,
            @Query("topic") String topic,
            @Query("token") String token,
            @Query("lang") String lang
    );
}