package com.epicodus.djmusicmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.djmusicmanager.R;
import com.epicodus.djmusicmanager.adapters.ResultListAdapter;
import com.epicodus.djmusicmanager.models.Song;
import com.epicodus.djmusicmanager.services.MusixService;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ResultsActivity extends AppCompatActivity{
    @Bind(R.id.noResultsTextView) TextView mNoResultsTextView;
    @Bind(R.id.resultsRecyclerView) RecyclerView mRecyclerView;
    private ResultListAdapter mAdapter;

    public static final String TAG = ResultsActivity.class.getSimpleName();

    public ArrayList<Song> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String songTitle = intent.getStringExtra("songTitle");
        String artistName = intent.getStringExtra("artistName");

        getTracks(songTitle, artistName);
    }


    private void getTracks(String songTitle, String artistName) {
        final MusixService musixService = new MusixService();
        musixService.findSongs(songTitle, artistName, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                songs = musixService.processResults(response);
                ResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (songs.size() == 0) {
                            mNoResultsTextView.setText("No results - please go back and try again");
                        } else {
                            mAdapter = new ResultListAdapter(getApplicationContext(), songs);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResultsActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                    }
                });
            }
        });
    }
}
