package com.epicodus.djmusicmanager.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.djmusicmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchTitleTextView) TextView mSearchTitleTextView;
    @Bind(R.id.songTitleEditText) EditText mSongTitleEditText;
    @Bind(R.id.artistNameEditText) EditText mArtistNameEditText;
    @Bind(R.id.searchAPIButton) Button mSearchAPIButton;
    @Bind(R.id.searchYouTubeButton) Button mSearchYouTubeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mSearchTitleTextView.setTypeface(boolackFont);

        mSearchAPIButton.setOnClickListener(this);
        mSearchYouTubeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == mSearchAPIButton) {
            String songTitle = mSongTitleEditText.getText().toString();
            String artistName = mArtistNameEditText.getText().toString();
            Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
            intent.putExtra("songTitle", songTitle);
            intent.putExtra("artistName", artistName);
            if (songTitle.equals("")){
                Toast.makeText(SearchActivity.this, "Please enter song title", Toast.LENGTH_LONG).show();
            } else {
                startActivity(intent);
            }
        } else if (v == mSearchYouTubeButton) {
            String songTitle = mSongTitleEditText.getText().toString();
            String artistName = mArtistNameEditText.getText().toString();
            if (songTitle.equals("")) {
                Toast.makeText(SearchActivity.this, "Please enter song title", Toast.LENGTH_LONG).show();
            } else if (!artistName.equals("")) {
                String searchStr = (songTitle.replaceAll(" ", "+") + "+" + artistName.replaceAll(" ", "+"));
                Uri webpage = Uri.parse("http://www.youtube.com/results?search_query=" + searchStr);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            } else {
                String searchTitle = songTitle.replaceAll(" ", "+");
                Uri webpage = Uri.parse("http://www.youtube.com/results?search_query=" + searchTitle);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        }
    }
}
