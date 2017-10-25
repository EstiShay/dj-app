package com.epicodus.djmusicmanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity{
    @Bind(R.id.resultsListView) ListView mResultsListView;
    @Bind(R.id.searchedTitleTextView) TextView mSearchedTitleTextView;

//    private TextView mSongTitleTextView;
//    private ListView mResultsListView;
    public static final String TAG = ResultsActivity.class.getSimpleName();

    private String[] songs = new String[] {"Solitaire", "This Year", "Just in Time", "You Were Cool",
            "Gimme a Pigfoot", "Up the Wolves", "Do I Move You?", "Love Love Love", "Sugar in My Bowl",
            "California Song", "Nobody's Fault But Mine", "California Song", "I Wish I Knew How it Would Feel to be Free",
            "Cubs in Five", "Feelin' Good", "The Diaz Brothers", "Since I Fell For You", "Sax Rohmer", "Lilac Wine", "Woke Up New"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songs);
        mResultsListView.setAdapter(adapter);

        Intent intent = getIntent();
        String songTitle = intent.getStringExtra("songTitle");
        String artistName = intent.getStringExtra("artistName");
        if (!artistName.equals("")){
            mSearchedTitleTextView.setText("You searched: " + songTitle + " by " + artistName);
        } else {
            mSearchedTitleTextView.setText("You searched: " + songTitle);
        }
    }


//    private void getTracks(String songTitle, String artistName) {
//        final MusixService musixService = new MusixService();
//        musixService.findSongs(songTitle, artistName, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    String jsonData = response.body().string();
//                    Log.v(TAG, jsonData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//    }
}
