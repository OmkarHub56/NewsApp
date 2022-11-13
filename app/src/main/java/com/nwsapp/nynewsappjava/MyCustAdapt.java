package com.nwsapp.nynewsappjava;

import static android.text.Html.fromHtml;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyCustAdapt extends RecyclerView.Adapter<MyCustviewHolder> {
    List<NewsItem> items;
    Context context;
    int type=1;
    public MyCustAdapt(List<NewsItem> items,Context context,int type){
        this.context=context;
        this.items=items;
        this.type=type;
    }

//    public void prt(){
////        for(int i=0;i<items.size();i++){
////            Log.i("loo",""+items.get(i).getAuthor());
////            Log.i("loo",""+items.get(i).getDescription());
////            Log.i("loo",""+items.get(i).getPublishedAt());
////            Log.i("loo",""+items.get(i).getTitle());
////            Log.i("loo",""+items.get(i).getUrl());
////            Log.i("loo",""+items.get(i).getUrlToImage());
////            Log.i("loo","");
////            Log.i("loo","");
////            Log.i("loo","");
////        }
//    }

    @NonNull
    @Override
    public MyCustviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.nws_item,parent,false);
        return new MyCustviewHolder(ll);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyCustviewHolder holder, int position) {

        // title
        holder.title.setText(items.get(position).getTitle());

        // published at
        String td=items.get(position).getPublishedAt();
        String time="",date="";
        if(type==1){
            time=td.substring(11,16);
            date=td.substring(0,10);
            holder.published_at.setText("Time : "+time+"  Date : "+date);
        }
        else{
            holder.published_at.setText(td);
        }

        // image
        if(items.get(position).getUrlToImage()==null){  // if image is null
            holder.img.setImageResource(R.drawable.no_image_avail);
        }
        else{
            // set the image using glide library
            Glide.with(context).load(items.get(position).getUrlToImage()).into(holder.img);
        }

        // set Author
        if(items.get(position).getAuthor()==null){
            items.get(position).setAuthor("Author not available");
        }
        holder.autr.setText(items.get(position).getAuthor());

        // set description
        if(items.get(position).getDescription()==null){
            Log.i("TUI","is null");
            holder.description.setText("description not available");
        }
        else{

            Log.i("TUI","is not null");
            SpannableString description = new SpannableString(items.get(position).getDescription());
            if(description.length()>0){
                description.setSpan(new RelativeSizeSpan(2f),0,1,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                holder.description.setText(description);
            }
            else{
                holder.description.setText("NA");
            }
        }

        setMargin(holder.ll,0,15,0,0);

//        Button btn=holder.btn;
//        btn.setOnClickListener(view -> {
//            I
//        });
        // to open the particular clicked news
        String finalTime = time;
        String finalDate = date;
        holder.ll.setOnClickListener(view -> {
            Intent intent = new Intent(context, SingleNewsWindow.class);
            intent.putExtra("title", items.get(position).getTitle());
            intent.putExtra("description", items.get(position).getDescription());
            intent.putExtra("content", items.get(position).getContent());
            intent.putExtra("author", items.get(position).getAuthor());
            intent.putExtra("imageUrl", items.get(position).getUrlToImage());
            if (type == 1) {
                intent.putExtra("publishedAt", "Time : " + finalTime + "  Date : " + finalDate);
            } else {
                intent.putExtra("publishedAt", td);
            }
            intent.putExtra("url", items.get(position).getUrl());
            intent.putExtra("type",type);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setMargin(View view,int left,int top,int right,int bottom){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        float dpRatio = context.getResources().getDisplayMetrics().density;
        int pixelForDp = (int)(dpRatio);
        params.setMargins(pixelForDp*left,pixelForDp*top,pixelForDp*right,pixelForDp*bottom);
        view.setLayoutParams(params);
    }
}

class MyCustviewHolder extends RecyclerView.ViewHolder {
    LinearLayout ll;
    TextView title;
    ImageView img;
    TextView published_at;
    TextView link;
    TextView autr;
    TextView description;
    public MyCustviewHolder(@NonNull View itemView) {
        super(itemView);
        ll= (LinearLayout) itemView;
        title=ll.findViewById(R.id.txtm);
        img=ll.findViewById(R.id.imgm);
        autr=ll.findViewById(R.id.authr);
        published_at=ll.findViewById(R.id.publsh);
        description=ll.findViewById(R.id.dsrc);
    }
}
