package com.nwsapp.nynewsappjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    int screenHeight,screenWidth;
    float screenDensity;

    String api_key="48b79801c0e1430eb1ddc82ad40b8e58";
    String gapi_key="621e8e8a261b304825fc161713d3c229";

    // the reference of the Activity
//    MainActivity mc;
    // the parent layout of the fragment
    LinearLayout parent_ll;
    LinearLayout breaking_news_ll;
    LinearLayout trending_now_ll;

    LinearLayout trending_news_title,breaking_news_title;
    HorizontalScrollView breaking_scrollview;
    ScrollView trending_scrollview;
    FrameLayout custom_search_fl;
    RecyclerView custom_news_recyclerview;

    List<NewsItem> list;
    MyCustAdapt mydpt;

    ShimmerFrameLayout sfl,sfl2;
    LinearLayout shm1,shm2;
    TextView showing_res;
    LinearLayout custom_sr_ll;

    public FragmentHome(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pl("Home fragment onCreateView");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenDensity=displayMetrics.density;
        screenHeight=displayMetrics.heightPixels;
        screenWidth=displayMetrics.widthPixels;


        // Inflate the layout for this fragment
        parent_ll= (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
        breaking_news_ll=parent_ll.findViewById(R.id.breaking_news_ll);
        trending_now_ll=parent_ll.findViewById(R.id.trending_now_ll);
        showing_res=parent_ll.findViewById(R.id.showing_res);
        custom_sr_ll=parent_ll.findViewById(R.id.custom_sr_ll);

        breaking_news_title=parent_ll.findViewById(R.id.breaking_news_title);
        trending_news_title=parent_ll.findViewById(R.id.trending_news_title);
        breaking_scrollview=parent_ll.findViewById(R.id.my_scrol);
        breaking_scrollview.setVisibility(View.GONE);
        trending_scrollview=parent_ll.findViewById(R.id.trending_scrollview);
        trending_scrollview.setVisibility(View.GONE);
        trending_scrollview.setVerticalFadingEdgeEnabled(true);
        trending_scrollview.setFadingEdgeLength(100);
        custom_search_fl=parent_ll.findViewById(R.id.custom_search_fl);
        custom_news_recyclerview=parent_ll.findViewById(R.id.recyly);
        custom_news_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        sfl=parent_ll.findViewById(R.id.sfl);
        shm1=parent_ll.findViewById(R.id.demo_shimmer);
        for(int i=0;i<2;i++){
            LinearLayout ll1= (LinearLayout) getLayoutInflater().inflate(R.layout.one_breaking_news_panel_shimmer,shm1,false);
            ll1.getLayoutParams().width= (int) (screenWidth-38*screenDensity);
            shm1.addView(ll1);
        }

        sfl2=parent_ll.findViewById(R.id.sfl2);
        shm2=parent_ll.findViewById(R.id.demo_shimmer2);
        for(int i=0;i<4;i++){
            LinearLayout ll1= (LinearLayout) getLayoutInflater().inflate(R.layout.trending_now_news_item_shimmer,shm2,false);
//            ll1.getLayoutParams().width= (int) (screenWidth-38*screenDensity);
            shm2.addView(ll1);
        }


//        NestedScrollView
//        shm2=parent_ll.findViewById(R.id.shm2);

        list=new ArrayList<>();
        custom_news_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        Log.i(TAG,"helo");
        mydpt = new MyCustAdapt(list,getContext(),1);
//        load=(ConstraintLayout)inflater.inflate(R.layout.loading_screen,container,false);
        custom_news_recyclerview.setAdapter(mydpt);

        ApiUtilities2.getApiInterface().getNews(3,"breaking-news",gapi_key,"en").enqueue(new Callback<GFullNews>() {
            @Override
            public void onResponse(Call<GFullNews> call, Response<GFullNews> response) {
                breaking_scrollview.setVisibility(View.VISIBLE);
                sfl.setVisibility(View.GONE);

//                pl("gg");
//                pl(response);
//                int bc=Math.min(3,response.body().articles.size());
//                GFullNews gf=response.body();
////                pl("totart ="+response.body().articles.size());
////                pl("bc ="+bc);
//                for(int i=0;i<bc;i++){
//                    LinearLayout temp= (LinearLayout) getLayoutInflater().inflate(R.layout.one_breaking_news_panel,breaking_news_ll,false);
//                    int finalI = i;
//                    temp.setOnClickListener(view -> {
//                        Intent intent=new Intent(getActivity(),SingleNewsWindow.class);
//                        intent.putExtra("title",gf.articles.get(finalI).title);
//                        intent.putExtra("description",gf.articles.get(finalI).description);
//                        intent.putExtra("content",gf.articles.get(finalI).content);
//                        intent.putExtra("author",gf.articles.get(finalI).source.name);
//                        intent.putExtra("imageUrl",gf.articles.get(finalI).image);
//                        String td=gf.articles.get(finalI).getPublishedAt();
//                        String time=td.substring(11,16),date=td.substring(0,10);
//                        intent.putExtra("publishedAt","Time : "+time+"  Date : "+date);
//                        intent.putExtra("url",gf.articles.get(finalI).url);
//                        getActivity().startActivity(intent);
//                    });
//                    TextView title=temp.findViewById(R.id.title_show);
//                    title.setText(response.body().articles.get(i).title);
//                    ImageView img=temp.findViewById(R.id.img_show);
//                    img.setClipToOutline(true);
//
//
//                    //to set the width of imageView to be the width of the screen - margin of 20dp from both sides
//                    img.getLayoutParams().width= (int) (screenWidth-40*screenDensity);
//                    Glide.with(getActivity()).load(response.body().articles.get(i).image).into(img);
//                    Log.i("toc","shown");
//                    breaking_news_ll.addView(temp);
//                }

//                breaking_news_text.setText(response.body().articles.get(0).title);
            }

            @Override
            public void onFailure(Call<GFullNews> call, Throwable t) {

            }
        });

        fillTrendingNews();
        return parent_ll;
    }

//    public void showAllNews(){
//        ApiUtilities.getApiInterface().getNews(100,"general",api_key,"in").enqueue(new Callback<FullNews>() {
//            @Override
//            public void onResponse(Call<FullNews> call, Response<FullNews> response) {
//                Log.i(TAG,String.valueOf(response));
//                if(response.isSuccessful()){
//                    list.clear();
//                    list.addAll(response.body().getArticles());
////                    mydpt.prt();
////                    Log.i(TAG,"helo1");
//                    mydpt.notifyDataSetChanged();
//                    load.setVisibility(View.GONE);
////                    for(int i=0;i<list.size();i++){
////                        Log.i(TAG,String.valueOf(i)+"....."+list.get(i).getUrlToImage());
////                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FullNews> call, Throwable t) {
//                Toast.makeText(getContext(), "Please check your network connection", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    public void fillTrendingNews(){
        ApiUtilities2.getApiInterface().getNews(6,"world",gapi_key,"en").enqueue(new Callback<GFullNews>() {
            @Override
            public void onResponse(Call<GFullNews> call, Response<GFullNews> response) {
                trending_scrollview.setVisibility(View.VISIBLE);
                sfl2.setVisibility(View.GONE);

//                GFullNews gf=response.body();
//                for(int i=0;i<6;i++){
//                    pl("yuion",response.toString());
//                    LinearLayout new_trend= (LinearLayout) getLayoutInflater().inflate(R.layout.trending_now_news_item,trending_now_ll,false);
//                    int finalI = i;
//                    new_trend.setOnClickListener(view -> {
//                        Intent intent=new Intent(getActivity(),SingleNewsWindow.class);
//                        intent.putExtra("title",gf.articles.get(finalI).title);
//                        intent.putExtra("description",gf.articles.get(finalI).description);
//                        intent.putExtra("content",gf.articles.get(finalI).content);
//                        intent.putExtra("author",gf.articles.get(finalI).source.name);
//                        intent.putExtra("imageUrl",gf.articles.get(finalI).image);
//                        String td=gf.articles.get(finalI).getPublishedAt();
//                        String time=td.substring(11,16),date=td.substring(0,10);
//                        intent.putExtra("publishedAt","Time : "+time+"  Date : "+date);
//                        intent.putExtra("url",gf.articles.get(finalI).url);
//                        getActivity().startActivity(intent);
//                    });
//                    TextView title=new_trend.findViewById(R.id.title);
//                    title.setText(response.body().articles.get(i).title);
//                    TextView author=new_trend.findViewById(R.id.author);
//                    author.setText(response.body().articles.get(i).source.name);
//                    ImageView img=new_trend.findViewById(R.id.img);
//                    img.setClipToOutline(true);
//                    Glide.with(getActivity()).load(response.body().articles.get(i).image).into(img);
//                    trending_now_ll.addView(new_trend);
//                }
            }

            @Override
            public void onFailure(Call<GFullNews> call, Throwable t) {

            }
        });
    }

    public void showSearchNews(String search){
        breaking_news_title.setVisibility(View.GONE);
        trending_news_title.setVisibility(View.GONE);
        breaking_scrollview.setVisibility(View.GONE);
        trending_scrollview.setVisibility(View.GONE);

        SpannableString str=new SpannableString("Showing results for '"+search+"'");
        pl("ye",search.length()-1);
        str.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.purple_200)),21,str.length()-1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        showing_res.setText(str);

        ProgressDialog dialog = ProgressDialog.show(getActivity(), "Please wait",
                "Fetching news for '"+search+"'", true);

        custom_sr_ll.setVisibility(View.VISIBLE);
        ApiUtilities.getApiInterface().getKeywordNews(api_key,100,search,"us").enqueue(new Callback<FullNews>() {
            @Override
            public void onResponse(Call<FullNews> call, Response<FullNews> response) {
//                for(int i=0;i<6;i++){
//                    LinearLayout new_trend= (LinearLayout) getLayoutInflater().inflate(R.layout.trending_now_news_item,trending_now_ll,false);
//                    TextView title=new_trend.findViewById(R.id.title);
//                    title.setText(response.body().articles.get(i).title);
//                    TextView author=new_trend.findViewById(R.id.author);
//                    author.setText(response.body().articles.get(i).source.name);
//                    ImageView img=new_trend.findViewById(R.id.img);
//                    Glide.with(mc).load(response.body().articles.get(i).image).into(img);
//                    trending_now_ll.addView(new_trend);
//                }
                if(response.isSuccessful()){
                    dialog.dismiss();
                    list.clear();
                    list.addAll(response.body().getArticles());
//                    mydpt.prt();
//                    Log.i(TAG,"helo1");
                    mydpt.notifyDataSetChanged();
//                    load.setVisibility(View.GONE);
//                    for(int i=0;i<list.size();i++){
//                        Log.i(TAG,String.valueOf(i)+"....."+list.get(i).getUrlToImage());
//                    }
                }
            }

            @Override
            public void onFailure(Call<FullNews> call, Throwable t) {


            }
        });
    }

    public void showBreakingAndTrending(){
        breaking_news_title.setVisibility(View.VISIBLE);
        trending_news_title.setVisibility(View.VISIBLE);
        breaking_scrollview.setVisibility(View.VISIBLE);
        trending_scrollview.setVisibility(View.VISIBLE);

        custom_sr_ll.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        pl("Home Fragment onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pl("Home fragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pl("Home fragment onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        pl("Home Fragment onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        pl("Home fragment onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        pl("Home fragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        pl("Home fragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        pl("Home fragment onDestroyed");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        pl("Home fragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        pl("Home Fragment onDetach");
        super.onDetach();
    }

    public void pl(Object log){
        Log.i("OMK",String.valueOf(log));
    }
    public void pl(String log){
        Log.i("OMK",log);
    }
    public void pl(String tag,Object log){
        Log.i(tag,String.valueOf(log));
    }
    public void pl(String tag,String log){
        Log.i(tag,log);
    }
//    public void st(Object toast){
//        Toast.makeText(context, String.valueOf(toast), Toast.LENGTH_SHORT).show();
//    }
//    public void st(String toast){
//        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
//    }
}