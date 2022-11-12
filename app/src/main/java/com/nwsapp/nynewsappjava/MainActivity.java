package com.nwsapp.nynewsappjava;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    String TAG="OMK";
    String gapi_key="621e8e8a261b304825fc161713d3c229";
//    String curr_sel_tab="Home";
    TabLayout tab_layout;
    ViewPager2 view_pager;
    String []genres=new String[]{"Home","General","Health","Business","Science","Sports","Tech"};
//    FragmentHome home_f;
//    FragmentGeneral gen_f;
//    FragmentBusiness business_f;
//    FragmentSports sports_f;
//    FragmentScience science_f;
//    FragmentHealth health_f;
//    FragmentTech tech_f;
    int screen_height,screen_width;
//    FrameLayout fmat;
//    Fragment curr_vis;
    TabLayout.Tab old_tab=null;
    EditText search_news;
    MyFragmentAdapter mpa;
    int curr_sel_tab=0;
    TabLayout.Tab home_tab,gen_tab,health_tab,bus_tab,science_tab,sports_tab,tech_tab;
//    int tab_ind_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"OnCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screen_height = displayMetrics.heightPixels;
        screen_width = displayMetrics.widthPixels;

        tab_layout=findViewById(R.id.tab_layout);
        tab_layout.addTab(tab_layout.newTab().setText("Home"));
        tab_layout.addTab(tab_layout.newTab().setText("General"));
        tab_layout.addTab(tab_layout.newTab().setText("Health"));
        tab_layout.addTab(tab_layout.newTab().setText("Business"));
        tab_layout.addTab(tab_layout.newTab().setText("Science"));
        tab_layout.addTab(tab_layout.newTab().setText("Sports"));
        tab_layout.addTab(tab_layout.newTab().setText("Tech"));
        tab_layout.addTab(tab_layout.newTab());
//        tab_ind_height=tab_layout.getTabSelectedIndicator().getMinimumHeight();
//        pl("pq",tab_ind_height);
//        tab_ind_height=50;
//        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                int pos=tab.getPosition();
////
////                if(pos!=curr_sel_tab){
////                    Log.i("hyn","hua");
////                    TabLayout.Tab tb=tab_layout.getTabAt(curr_sel_tab);
////                    tb.setCustomView(null);
////                    tb.setCustomView(R.layout.custom_tab_selected);
////                    TextView tv=(TextView) tb.getCustomView();
////                    tv.setTextColor(Color.BLACK);
////                    tv.setTextSize(15);
////                }
//////                tab.set
////                pl("hyn","pos : "+pos);
////                tab.setCustomView(null);
////                tab.setCustomView(R.layout.custom_tab_selected);
////                TextView tv2=(TextView) tab.getCustomView();
////                tv2.setText(tab.getText());
////                tv2.setTextSize(18);
////
////                curr_sel_tab=pos;
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                pl("hu","ui");
//            }
//        });

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pl("rect",tab.getText()+" tab clicked");
                view_pager.setCurrentItem(tab.getPosition(),true);
//                tab_layout.selectTab(tab,true);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        tab_layout.addTab(tab_layout.newTab().setText("Home"));
//        tab_layout.addTab(tab_layout.newTab().setText("General"));
//        tab_layout.addTab(tab_layout.newTab().setText("Health"));
//        tab_layout.addTab(tab_layout.newTab().setText("Business"));
//        tab_layout.addTab(tab_layout.newTab().setText("Science"));
//        tab_layout.addTab(tab_layout.newTab().setText("Sports"));
//        tab_layout.addTab(tab_layout.newTab().setText("Tech"));

//        tab_layout.setScrollPosition();
//        tab_layout.setSelectedTabIndicator(R.drawable.solid_rounded_white);
        mpa=new MyFragmentAdapter(getSupportFragmentManager(),getLifecycle());
        view_pager=findViewById(R.id.myvp);
        view_pager.setOffscreenPageLimit(6);
        view_pager.setAdapter(mpa);
        view_pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                pl("rect","selected "+genres[position]+" tab");
                tab_layout.selectTab(tab_layout.getTabAt(position),true);
//                tab_layout.setScrollPosition(position,0f,true);

//                tab_layout.setSelectedTabIndicator(R.drawable.solid_rounded_white);
                if(curr_sel_tab==7){
                    ImageView img= (ImageView) tab_layout.getTabAt(curr_sel_tab).getCustomView();
                    img.setImageResource(R.drawable.bookmark_off_logo_64x64);
                }
                else{
                    TabLayout.Tab last_tab=tab_layout.getTabAt(curr_sel_tab);
                    TextView tv1=(TextView) last_tab.getCustomView();
                    tv1.setText(last_tab.getText());
                    tv1.setTextSize(15);
                    tv1.setTextColor(getResources().getColor(R.color.dddark_gray));
                }

                if(position==7){
                    ImageView img= (ImageView) tab_layout.getTabAt(position).getCustomView();
                    img.setImageResource(R.drawable.bookmark_on_logo_64x64);
                    tab_layout.setSelectedTabIndicatorColor(Color.WHITE);
                }
                else{
                    TabLayout.Tab tab=tab_layout.getTabAt(position);
                    TextView tv=(TextView) tab.getCustomView();
                    tv.setText(tab.getText());
                    tv.setTextSize(18);
                    tv.setTextColor(Color.WHITE);
                    tab_layout.setSelectedTabIndicatorColor(getResources().getColor(R.color.purple_500));
                }
                curr_sel_tab=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pl("uib","uij");
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

        for(int i=0;i<7;i++){
            pl("hr","df");
            tab_layout.getTabAt(i).setCustomView(R.layout.custom_tab_selected);
            TextView tv=(TextView) tab_layout.getTabAt(i).getCustomView();
            tv.setText(genres[i]);
            tv.setTextSize(15);
            tv.setTextColor(getResources().getColor(R.color.dddark_gray));
        }
        tab_layout.getTabAt(7).setCustomView(R.layout.custom_view_for_bookmarks);

//        new TabLayoutMediator(tab_layout,
//                view_pager,
//                (tab, position) -> {
//                    tab.setText(genres[position]);
//                    tab.setCustomView(R.layout.custom_tab_selected);
//                    ((TextView)tab.getCustomView()).setText(tab.getText());
//                }).attach();

//        home=findViewById(R.id.home);
//        general=findViewById(R.id.general);
//        health=findViewById(R.id.health);
//        business=findViewById(R.id.business);
//        science=findViewById(R.id.science);
//        sports=findViewById(R.id.sports);
//        tech=findViewById(R.id.tech);
//        fmat=findViewById(R.id.frame_l);
        search_news=findViewById(R.id.editTextTextPersonName);
        search_news.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i==EditorInfo.IME_ACTION_SEARCH){
                showSearchNews(search_news.getText().toString());
                //do here your stuff f
                return true;
            }
            return false;
        });

//        home_f=new FragmentHome();
//        gen_f=new FragmentGeneral();
//        business_f=new FragmentBusiness();
//        sports_f=new FragmentSports();
//        health_f=new FragmentHealth();
//        science_f=new FragmentScience();
//        tech_f=new FragmentTech();

        // loading the initial home fragment
//        curr_vis=home_f;
//        FragmentManager fmg=getSupportFragmentManager();
//        FragmentTransaction fmt= fmg.beginTransaction();
//        fmt.add(R.id.frame_l,home_f);
//        fmt.add(R.id.frame_l,gen_f);
//        fmt.add(R.id.frame_l,health_f);
//        fmt.add(R.id.frame_l,business_f);
//        fmt.add(R.id.frame_l,science_f);
//        fmt.add(R.id.frame_l,sports_f);
//        fmt.add(R.id.frame_l,tech_f);
//        fmt.hide(gen_f);
//        fmt.hide(health_f);
//        fmt.hide(business_f);
//        fmt.hide(science_f);
//        fmt.hide(sports_f);
//        fmt.hide(tech_f);
//        fmt.commit();

//        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                String a=tab.getText().toString();
//                if(a.equals("Home")){
//                    FragmentManager fmg=getSupportFragmentManager();
//                    FragmentTransaction fmt= fmg.beginTransaction();
//                    fmt.show(home_f);
//                    home_f.showBreakingAndTrending();
//                    fmt.hide(curr_vis);
//                    curr_vis=home_f;
//                    fmt.commit();
//                }
//                else if(a.equals("General")){
//                    FragmentManager fmg=getSupportFragmentManager();
//                    FragmentTransaction fmt= fmg.beginTransaction();
//                    fmt.show(gen_f);
//                    fmt.hide(curr_vis);
//                    curr_vis=gen_f;
//                    fmt.commit();
//                }
//                else if(a.equals("Health")){
//                    FragmentManager fmg=getSupportFragmentManager();
//                    FragmentTransaction fmt= fmg.beginTransaction();
//                    fmt.show(health_f);
//                    fmt.hide(curr_vis);
//                    curr_vis=health_f;
//                    fmt.commit();
//                }
//                else if(a.equals("Business")){
//                    FragmentManager fmg=getSupportFragmentManager();
//                    FragmentTransaction fmt= fmg.beginTransaction();
//                    fmt.show(business_f);
//                    fmt.hide(curr_vis);
//                    curr_vis=business_f;
//                    fmt.commit();
//                }
//                else if(a.equals("Science")){
//                    FragmentManager fmg=getSupportFragmentManager();
//                    FragmentTransaction fmt= fmg.beginTransaction();
//                    fmt.show(science_f);
//                    fmt.hide(curr_vis);
//                    curr_vis=science_f;
//                    fmt.commit();
//                }
//                else if(a.equals("Sports")){
//                    FragmentManager fmg=getSupportFragmentManager();
//                    FragmentTransaction fmt= fmg.beginTransaction();
//                    fmt.show(sports_f);
//                    fmt.hide(curr_vis);
//                    curr_vis=sports_f;
//                    fmt.commit();
//                }
//                else{
//                    FragmentManager fmg=getSupportFragmentManager();
//                    FragmentTransaction fmt= fmg.beginTransaction();
//                    fmt.show(tech_f);
//                    fmt.hide(curr_vis);
//                    curr_vis=tech_f;
//                    fmt.commit();
//                }
//                pl("Item count : "+fmat.getChildCount());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    public void showSearchNews(String search){
        mpa.getCurrFragment().showSearchNews(search);
//        hom.showSearchNews(search);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume called");
        super.onResume();
    }
    @Override
    protected void onStart() {
        Log.i(TAG,"onStart called");
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        pl("onDestroy called");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        pl("onStop called");
        super.onStop();
    }

    @Override
    protected void onPause() {
        pl("onPause called");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        pl("pressed back");
        super.onBackPressed();
    }

    public void pl(String tag,Object log){
        Log.i(tag,String.valueOf(log));
    }
    public void pl(String tag,String log){
        Log.i(tag,log);
    }
    public void pl(Object log){
        Log.i(TAG,String.valueOf(log));
    }
    public void pl(String log){
        Log.i(TAG,log);
    }
    public void st(Object toast){
        Toast.makeText(this, String.valueOf(toast), Toast.LENGTH_SHORT).show();
    }
    public void st(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
