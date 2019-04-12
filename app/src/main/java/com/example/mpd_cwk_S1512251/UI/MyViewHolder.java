package com.example.mpd_cwk_S1512251.UI;

// Name                 Gavin Walter Jaap
// Student ID           S1512251
// Programme of Study   Computing

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mpd_cwk_S1512251.R;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView titleTxt,locatTxt,depthTxt,magTxt,linkTxt,dateTxt,latTxt,longTxt;
    OnNoteListener onNoteListener;
    public MyViewHolder(View itemView, OnNoteListener onNoteListener) {
        super(itemView);

        //titleTxt = itemView.findViewById(R.id.ReTitle);
        locatTxt = itemView.findViewById(R.id.ReLocate);
        depthTxt = itemView.findViewById(R.id.ReDepth);
        magTxt = itemView.findViewById(R.id.ReMag);
        linkTxt = itemView.findViewById(R.id.ReLink);
        dateTxt = itemView.findViewById(R.id.ReDate);
        //latTxt = itemView.findViewById(R.id.ReLat);
        //longTxt = itemView.findViewById(R.id.ReLong);
        this.onNoteListener = onNoteListener;
        itemView.setOnClickListener(this);

    }

    public MyViewHolder(View v) {
        super(v);
        //titleTxt = itemView.findViewById(R.id.ReTitle);
        locatTxt = itemView.findViewById(R.id.ReLocate);
        depthTxt = itemView.findViewById(R.id.ReDepth);
        magTxt = itemView.findViewById(R.id.ReMag);
        linkTxt = itemView.findViewById(R.id.ReLink);
        dateTxt = itemView.findViewById(R.id.ReDate);
        //latTxt = itemView.findViewById(R.id.ReLat);
        //longTxt = itemView.findViewById(R.id.ReLong);
        this.onNoteListener = onNoteListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        onNoteListener.onNoteClick(getAdapterPosition());
    }


    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
