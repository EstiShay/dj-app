package com.epicodus.djmusicmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.djmusicmanager.R;
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
    @Bind(R.id.resultsListView) ListView mResultsListView;
    @Bind(R.id.searchedTitleTextView) TextView mSearchedTitleTextView;

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
        if (!artistName.equals("")){
            mSearchedTitleTextView.setText("You searched: " + songTitle + " by " + artistName);
        } else {
            mSearchedTitleTextView.setText("You searched: " + songTitle);
        }
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
                        String[] songResults = new String[songs.size()];
                        for (int i = 0; i < songResults.length; i++){
                            songResults[i] = songs.get(i).getTitle() + " by " + songs.get(i).getArtist() + " (" + songs.get(i).getYear() + ")";
                        }
                        ArrayAdapter adapter = new ArrayAdapter(ResultsActivity.this, android.R.layout.simple_list_item_1, songResults);
                        mResultsListView.setAdapter(adapter);

                        for (Song song : songs ) {
                            Log.d(TAG, "Title: " + song.getTitle());
                            Log.d(TAG, "Artist: " + song.getArtist());
                            Log.d(TAG, "Album: " + song.getAlbum());
                            Log.d(TAG, "Year: " + song.getYear());
                        }
                    }
                });
            }
        });
    }
}
