package com.example.mpd_cwk_S1512251.UI;

// Name                 Gavin Walter Jaap
// Student ID           S1512251
// Programme of Study   Computing

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mpd_cwk_S1512251.DataObject.Article;
import com.example.mpd_cwk_S1512251.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context c;
    ArrayList<Article> articles;
    static ArrayList<Article> articlesFull;
    MyViewHolder.OnNoteListener mOnNoteListener;

    public MyAdapter(Context c, ArrayList<Article> articles, MyViewHolder.OnNoteListener onNoteListener) {
        this.c = c;
        this.articles = articles;
        articlesFull = new ArrayList<>(articles);
        this.mOnNoteListener = onNoteListener;
    }

    public MyAdapter(Context c, ArrayList<Article> articles) {
        this.c = c;
        this.articles = articles;
        articlesFull = new ArrayList<>(articles);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.recycle_listview, parent, false);
        return new MyViewHolder(v, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Article article = articles.get(position);


        String title = article.getTitle();
        String locat = article.getLocation();
        int lengthlocat = locat.length();
        String depth = article.getDepth();
        int lengthdepth = depth.length();
        String magni = article.getMagnitude();
        String link = article.getLink();
        String date = article.getDate();
        double latitude = article.getLatitude();
        double longitude = article.getLongitude();
        String lati = String.valueOf(latitude);
        String longi = String.valueOf(longitude);

        //holder.titleTxt.setText(title);
        holder.locatTxt.setText(locat.substring(0, lengthlocat));
        holder.depthTxt.setText(depth.substring(0, lengthdepth));
        holder.magTxt.setText(magni.substring(0, 3));
        holder.linkTxt.setText(link);
        holder.dateTxt.setText(date);
        // holder.latTxt.setText(lati);
        // holder.longTxt.setText(longi);

        String str1 = magni.substring(0, 3);
        boolean color = str1.contains("Mag");
        if (color == false) {

            double value = Double.parseDouble(str1);
            if (value <= 1.0) {
                holder.magTxt.setTextColor(Color.parseColor("#4be802"));
            } else if (value > 1.0 && value <= 2.0) {
                holder.magTxt.setTextColor(Color.parseColor("#e5b422"));
            } else if (value > 2.0 && value <= 10.0) {
                holder.magTxt.setTextColor(Color.parseColor("#f72b02"));
            }
        }

    }

    public void setFilter(ArrayList<Article> filteredList) {
        articles = new ArrayList<>();
        articles.addAll(filteredList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

}