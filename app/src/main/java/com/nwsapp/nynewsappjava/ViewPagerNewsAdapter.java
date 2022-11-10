package com.nwsapp.nynewsappjava;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerNewsAdapter extends FragmentStateAdapter {
    public ViewPagerNewsAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        if(position==0){
//            return new FragmentHome();
//        }
//        else if(position==1){
//            return new FragmentHealth();
//        }
//        else if(position==2){
//            return new FragmentBusiness();
//        }
//        else if(position==3){
//            return new FragmentScience();
//        }
//        else if(position==4){
//            return new FragmentSports();
//        }
//        else{
            return new FragmentTech();
//        }
    }

    @Override
    public int getItemCount() {
        // no of tabs
        return 7;
    }
}
