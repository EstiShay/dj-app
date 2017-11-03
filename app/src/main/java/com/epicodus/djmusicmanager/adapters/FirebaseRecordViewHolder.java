package com.epicodus.djmusicmanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.djmusicmanager.R;
import com.epicodus.djmusicmanager.models.Record;

public class FirebaseRecordViewHolder extends RecyclerView.ViewHolder{
    View mView;
    Context mContext;
    public TextView mRecordTitleArtistTextView;

    public FirebaseRecordViewHolder (View songView) {
        super(songView);
        mView = songView;
        mContext = songView.getContext();
    }

    public void bindRecord(Record record) {
        mRecordTitleArtistTextView = mView.findViewById(R.id.songTitleArtistTextView);
        TextView albumYearTextView = mView.findViewById(R.id.albumYearTextView);

        mRecordTitleArtistTextView.setText(record.getRecTitle() + " by " + record.getRecArtist());
        if (!record.getRecAlbum().equals("") && !record.getRecYear().equals("")){
            albumYearTextView.setText(record.getRecAlbum() + " released " + record.getRecYear());
        } else if (record.getRecYear().equals("")){
            albumYearTextView.setText(record.getRecAlbum());
        } else if (record.getRecAlbum().equals("")){
            albumYearTextView.setText("Released " + record.getRecYear());
        }

    }


}
