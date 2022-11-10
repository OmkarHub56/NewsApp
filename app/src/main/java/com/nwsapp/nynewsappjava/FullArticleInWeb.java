package com.nwsapp.nynewsappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FullArticleInWeb extends AppCompatActivity {
    String news_url;
    WebView news_article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_article_in_web);
        getSupportActionBar().hide();

        Intent intent=getIntent();
        news_url=intent.getStringExtra("url");
        news_article=findViewById(R.id.webView);
//        Log.i("YU",n)

        news_article.getSettings().setJavaScriptEnabled(true);

        news_article.setWebViewClient(new WebViewClient());

        news_article.loadUrl(news_url);
    }
}