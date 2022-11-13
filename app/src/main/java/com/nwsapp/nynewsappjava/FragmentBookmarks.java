package com.nwsapp.nynewsappjava;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBookmarks extends Fragment {

//    String api_key="48b79801c0e1430eb1ddc82ad40b8e58";
    RecyclerView rv;
    List<NewsItem> list;
    MyCustAdapt mydpt;
    ConstraintLayout load;
    ShimmerFrameLayout shm;
    MyDbHelper mdh;
    View parent_ll;
    public FragmentBookmarks() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent_ll=inflater.inflate(R.layout.fragment_bookmarks, container, false);

        mdh=MyDbHelper.getDatabase(getActivity());
//        List<OneBookMarkedNewsItem> list1=mdh.newsDao().getAllBookmarkedNews();
        list=new ArrayList<>();
//        for(int i=0;i<list1.size();i++){
//            list.add(new NewsItem(list1.get(i)));
//        }
        rv=parent_ll.findViewById(R.id.recyly);
        shm=parent_ll.findViewById(R.id.shm);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        Log.i(TAG,"helo");
//        mydpt = new MyCustAdapt(list,getContext(),0);
//        load=(ConstraintLayout)inflater.inflate(R.layout.loading_screen,container,false);
//        rv.setAdapter(mydpt);
//        showBookmarkedNews();

        return parent_ll;

    }

    @Override
    public void onResume() {
        super.onResume();
        List<OneBookMarkedNewsItem> list1=mdh.newsDao().getAllBookmarkedNews();
        list.clear();
        for(int i=0;i<list1.size();i++){
            list.add(new NewsItem(list1.get(i)));
        }
        mydpt = new MyCustAdapt(list,getContext(),0);
        rv.setAdapter(mydpt);
    }

    @Override
    public void onPause() {
        super.onPause();
        list.clear();
        mydpt.notifyDataSetChanged();
    }

    //    public void showBookmarkedNews(){
//        ApiUtilities.getApiInterface().getNews(100,"business",api_key,"in").enqueue(new Callback<FullNews>() {
//            @Override
//            public void onResponse(Call<FullNews> call, Response<FullNews> response) {
////                Log.i(TAG,String.valueOf(response));
//                if(response.isSuccessful()){
//                    rv.setVisibility(View.VISIBLE);
//                    shm.setVisibility(View.GONE);
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
}