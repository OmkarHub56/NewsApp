package com.nwsapp.nynewsappjava;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHealth extends Fragment {

    String TAG="OMK";
    String api_key="48b79801c0e1430eb1ddc82ad40b8e58";
    RecyclerView rv;
    List<NewsItem> list;
    MyCustAdapt mydpt;
    ConstraintLayout load;
    public FragmentHealth(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pl("Health fragment onCreateView");
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_health, container, false);
        rv=view.findViewById(R.id.recyly);
        list=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i(TAG,"helo");
        mydpt = new MyCustAdapt(list,getContext());
        load=(ConstraintLayout)inflater.inflate(R.layout.loading_screen,container,false);
        rv.setAdapter(mydpt);
        showAllNews();
        return view;

    }

    public void showAllNews(){
        ApiUtilities.getApiInterface().getNews(100,"health",api_key,"in").enqueue(new Callback<FullNews>() {
            @Override
            public void onResponse(Call<FullNews> call, Response<FullNews> response) {
                Log.i("gen",String.valueOf(response));
                if(response.isSuccessful()){
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
        pl("Health Fragment onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pl("Health fragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pl("Health fragment onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        pl("Health Fragment onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        pl("Health fragment onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        pl("Health fragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        pl("Health fragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        pl("Health fragment onDestroyed");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        pl("Health fragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        pl("Health Fragment onDetach");
        super.onDetach();
    }

    public void pl(Object log){
        Log.i(TAG,String.valueOf(log));
    }
    public void pl(String log){
        Log.i(TAG,log);
    }
}