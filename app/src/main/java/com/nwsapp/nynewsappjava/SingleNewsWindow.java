package com.nwsapp.nynewsappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SingleNewsWindow extends AppCompatActivity {
    String TAG="Ol";
    ImageView newsImage;
    ImageButton bookmark_shower;
    TextView title_shower,description_shower,content_shower;
    TextView author_shower,published_at_date;
    int bookmark_status=0;
    String news_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news_window);
        getSupportActionBar().hide();

        Intent intent=getIntent();

        newsImage=findViewById(R.id.n_image);
        bookmark_shower=findViewById(R.id.imageButton);
        title_shower=findViewById(R.id.textView2);
        description_shower=findViewById(R.id.news_description);
        content_shower=findViewById(R.id.news_content);
        author_shower=findViewById(R.id.news_author);
        published_at_date=findViewById(R.id.news_published_date);
        published_at_date.setText(intent.getStringExtra("publishedAt"));
        author_shower.setText(intent.getStringExtra("author"));
        String to_show_cont=intent.getStringExtra("content");
        to_show_cont=to_show_cont.substring(0,to_show_cont.lastIndexOf('['));
        content_shower.setText(to_show_cont);
        title_shower.setText(intent.getStringExtra("title"));
        description_shower.setText(intent.getStringExtra("description"));
        Glide.with(this).load(intent.getStringExtra("imageUrl")).into(newsImage);
        news_url=intent.getStringExtra("url");
    }

    public void showWebsite(View view){
        Intent intent=new Intent(this,FullArticleInWeb.class);
        intent.putExtra("url",news_url);
        startActivity(intent);
    }
    public void saveBookMark(View view){
        if(bookmark_status==0){
            bookmark_shower.setImageResource(R.drawable.bookmark_on_logo_64x64);
            bookmark_status=1;
            Toast.makeText(this, "Bookmark added", Toast.LENGTH_SHORT).show();
        }
        else{
            bookmark_shower.setImageResource(R.drawable.bookmark_off_logo_64x64);
            bookmark_status=0;
            Toast.makeText(this, "Bookmark removed", Toast.LENGTH_SHORT).show();
        }
    }

    public void pl(Object log){
        Log.i(TAG,String.valueOf(log));
    }
    public void pl(String log){
        Log.i(TAG,log);
    }
}