package com.epicodus.djmusicmanager.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.djmusicmanager.R;
import com.epicodus.djmusicmanager.models.Song;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ResultViewHolder>{
    private ArrayList<Song> mSongs = new ArrayList<>();
    private Context mContext;

    public ResultListAdapter(Context context, ArrayList<Song> songs) {
        mContext = context;
        mSongs = songs;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.songTitleArtistTextView) TextView mSongTitleArtistTextView;
        @Bind(R.id.albumYearTextView) TextView mAlbumYearTextView;

        private Context mContext;

        public ResultViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindSong(Song song){
            mSongTitleArtistTextView.setText(song.getTitle() + " by " + song.getArtist());
            mAlbumYearTextView.setText("Album: " + song.getAlbum() + ", released in " + song.getYear());

        }
    }

    @Override
    public ResultListAdapter.ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, parent, false);
        ResultViewHolder viewHolder = new ResultViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResultListAdapter.ResultViewHolder holder, int position){
        holder.bindSong(mSongs.get(position));
    }

    @Override
    public int getItemCount(){
        return mSongs.size();
    }

}
