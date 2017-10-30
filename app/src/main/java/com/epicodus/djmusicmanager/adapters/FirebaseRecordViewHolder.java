package com.epicodus.djmusicmanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.djmusicmanager.R;
import com.epicodus.djmusicmanager.models.Record;

public class FirebaseRecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;

    public FirebaseRecordViewHolder (View songView) {
        super(songView);
        mView = songView;
        mContext = songView.getContext();
        songView.setOnClickListener(this);
    }

    public void bindRecord(Record record) {
        TextView titleTextView = (TextView)mView.findViewById(R.id.songTitleTextView);
        TextView artistTextView = (TextView)mView.findViewById(R.id.artistNameTextView);
    }
}
