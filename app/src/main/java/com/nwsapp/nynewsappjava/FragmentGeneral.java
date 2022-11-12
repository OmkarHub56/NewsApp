package com.nwsapp.nynewsappjava;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGeneral extends Fragment {
    String TAG="OMK";
    String api_key="48b79801c0e1430eb1ddc82ad40b8e58";
    RecyclerView rv;
    List<NewsItem> list;
    MyCustAdapt mydpt;
    ConstraintLayout load;
    ShimmerFrameLayout shm;
    public FragmentGeneral() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pl("Gen Fragment onCreateView");
        View view=inflater.inflate(R.layout.fragment_general, container, false);
        rv=view.findViewById(R.id.recyly);
        shm=view.findViewById(R.id.shm);
        list=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i(TAG,"helo");
        mydpt = new MyCustAdapt(list,getContext(),1);
        load=(ConstraintLayout)inflater.inflate(R.layout.loading_screen,container,false);
        rv.setAdapter(mydpt);
        showAllNews();
        return view;
    }

    public void showAllNews(){
        ApiUtilities.getApiInterface().getNews(100,"general",api_key,"in").enqueue(new Callback<FullNews>() {
            @Override
            public void onResponse(Call<FullNews> call, Response<FullNews> response) {
//                Log.i(TAG,String.valueOf(response));
                if(response.isSuccessful()){
                    rv.setVisibility(View.VISIBLE);
                    shm.setVisibility(View.GONE);
                    list.clear();
                    list.addAll(response.body().getArticles());
//                    mydpt.prt();
//                    Log.i(TAG,"helo1");
                    mydpt.notifyDataSetChanged();
                    load.setVisibility(View.GONE);
//                    for(int i=0;i<list.size();i++){
//                        Log.i(TAG,String.valueOf(i)+"....."+list.get(i).getUrlToImage());
//                    }
                }
            }

            @Override
            public void onFailure(Call<FullNews> call, Throwable t) {
                Toast.makeText(getContext(), "Please check your network connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        pl("Gen Fragment onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pl("Gen fragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pl("Gen fragment onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        pl("Gen Fragment onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        pl("Gen fragment onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        pl("Gen fragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        pl("Gen fragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        pl("Gen fragment onDestroyed");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        pl("Gen fragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        pl("Gen Fragment onDetach");
        super.onDetach();
    }

    public void pl(Object log){
        Log.i(TAG,String.valueOf(log));
    }
    public void pl(String log){
        Log.i(TAG,log);
    }
//    public void st(Object toast){
//        Toast.makeText(this, String.valueOf(toast), Toast.LENGTH_SHORT).show();
//    }
//    public void st(String toast){
//        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
//    }
}