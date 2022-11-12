package com.nwsapp.nynewsappjava;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class MyFragmentAdapter extends FragmentStateAdapter {
    String TAG="OMK";
    FragmentHome currFrag;
    public MyFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MyFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        pl("aya");
        if(position==0){
            pl("Created                 new HomeF");
            currFrag=new FragmentHome();
            return currFrag;
        }
        else if(position==1){
            pl("Created             new GenF");
            return new FragmentGeneral();
        }
        else if(position==2){
            pl("Created           new HealthF");
            return new FragmentHealth();
        }
        else if(position==3){
            pl("Created           new BusinessF");
            return new FragmentBusiness();
        }
        else if(position==4){
            pl("Created           new ScienceF");
            return new FragmentScience();
        }
        else if(position==5){
            pl("Created           new SportsF");
            return new FragmentSports();
        }
        else if(position==6){
            pl("Created               new TechF");
            return new FragmentTech();
        }
        else if(position==7){
            pl("Created               new BookmarkF");
            return new FragmentBookmarks();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public FragmentHome getCurrFragment(){
        return currFrag;
    }

    public void pl(Object log){
        Log.i(TAG,String.valueOf(log));
    }
    public void pl(String log){
        Log.i(TAG,log);
    }
//    public void st(Object toast){
//        Toast.makeText(context, String.valueOf(toast), Toast.LENGTH_SHORT).show();
//    }
//    public void st(String toast){
//        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
//    }
}
